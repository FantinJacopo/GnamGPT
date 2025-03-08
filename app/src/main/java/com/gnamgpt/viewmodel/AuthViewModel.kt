package com.gnamgpt.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnamgpt.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val _isLoggedIn = MutableStateFlow(auth.currentUser != null)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun getGoogleSignInClient(activity: Activity): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity, gso)
    }

    fun handleGoogleSignInResult(data: Intent?, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        if (data == null) {
            android.util.Log.e("AUTH_DEBUG", "Data is null")
            onFailure("Nessun dato ricevuto dal login Google")
            return
        }
        
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account == null) {
                android.util.Log.e("AUTH_DEBUG", "Account is null")
                onFailure("Account Google non trovato")
                return
            }
            
            val idToken = account.idToken
            if (idToken == null) {
                android.util.Log.e("AUTH_DEBUG", "ID Token is null")
                onFailure("Token ID Google non trovato")
                return
            }
            
            android.util.Log.d("AUTH_DEBUG", "Got ID token, authenticating with Firebase")
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    android.util.Log.d("AUTH_DEBUG", "Firebase auth successful")
                    viewModelScope.launch { _isLoggedIn.emit(true) }
                    onSuccess()
                } else {
                    android.util.Log.e("AUTH_DEBUG", "Firebase auth failed", task.exception)
                    onFailure("Errore di autenticazione Firebase: ${task.exception?.message}")
                }
            }
        } catch (e: ApiException) {
            android.util.Log.e("AUTH_DEBUG", "Google sign in failed", e)
            val errorMessage = "Errore durante il login con Google (${e.statusCode}): ${e.message}"
            onFailure(errorMessage)
        }
    }

    fun logout() {
        auth.signOut()
        viewModelScope.launch { _isLoggedIn.emit(false) }
    }

    fun debugShowSha1Certificate(activity: Activity) {
        try {
            val info = activity.packageManager.getPackageInfo(
                activity.packageName,
                android.content.pm.PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures!!) {
                val md = java.security.MessageDigest.getInstance("SHA1")
                md.update(signature.toByteArray())
                val sha1 = android.util.Base64.encodeToString(md.digest(), android.util.Base64.DEFAULT)
                android.util.Log.d("AUTH_DEBUG", "SHA1: $sha1")
            }
        } catch (e: Exception) {
            android.util.Log.e("AUTH_DEBUG", "Error getting SHA1: ${e.message}")
        }
    }
}
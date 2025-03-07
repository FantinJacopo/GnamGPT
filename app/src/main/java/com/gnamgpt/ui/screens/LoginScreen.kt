package com.gnamgpt.ui.screens

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gnamgpt.R
import com.gnamgpt.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = viewModel(),
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as Activity

    val googleSignInClient = authViewModel.getGoogleSignInClient(activity)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        authViewModel.handleGoogleSignInResult(result.data, onSuccess = {
            onLoginSuccess()
        }, onFailure = {
            // TODO: Gestire l'errore
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: AGGIUNGERE logo dell'app
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo GnamGPT",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 24.dp)
        )
        
        Text(
            "GnamGPT",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Text(
            "Il tuo assistente culinario intelligente",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        // Pulsante Login con Google
        Button(
            onClick = { launcher.launch(googleSignInClient.signInIntent) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.DarkGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp)),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Utilizziamo Box per assicurarci che ci sia spazio per l'icona
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Logo",
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Text(
                    "Accedi con Google",
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pulsante "Continua senza account"
        OutlinedButton(
            onClick = onLoginSuccess,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
        ) {
            Text(
                "Continua senza account",
                fontWeight = FontWeight.Medium
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            "Accedendo, accetti i nostri Termini di servizio e la Privacy Policy",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginSuccess = {})
}
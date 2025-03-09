import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.gnamgpt.model.Recipe
import com.gnamgpt.viewmodel.MealViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    mealId: String,
    mealViewModel: MealViewModel = viewModel()
) {
    Log.d("RecipeDetailScreen", "onCreate: mealId = $mealId")
    val mealDetail by mealViewModel.mealDetail.collectAsState()

    // Carica i dettagli del piatto
    LaunchedEffect(mealId) {
        mealViewModel.loadMealDetail(mealId)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = mealDetail?.strMeal ?: "Loading...") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            mealDetail?.let { meal ->
                Image(
                    painter = rememberAsyncImagePainter(meal.strMealThumb),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = meal.strMeal ?: "N/A",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Categoria e Area
                Text(text = "Category: ${meal.strCategory ?: "N/A"}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Area: ${meal.strArea ?: "N/A"}", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(16.dp))

                // Ingredienti
                IngredientsSection(meal = meal)

                Spacer(modifier = Modifier.height(16.dp))

                // Istruzioni
                Text(
                    text = "Instructions",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = meal.strInstructions ?: "N/A",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )
            } ?: run {
                Text("Failed to load recipe details")
            }
        }
    }
}

@Composable
fun IngredientsSection(meal: Recipe) {
    val ingredients = listOfNotNull(
        meal.strIngredient1?.let { Pair(it, meal.strMeasure1) },
        meal.strIngredient2?.let { Pair(it, meal.strMeasure2) },
        meal.strIngredient3?.let { Pair(it, meal.strMeasure3) },
        meal.strIngredient4?.let { Pair(it, meal.strMeasure4) },
        meal.strIngredient5?.let { Pair(it, meal.strMeasure5) },
        meal.strIngredient6?.let { Pair(it, meal.strMeasure6) },
        meal.strIngredient7?.let { Pair(it, meal.strMeasure7) },
        meal.strIngredient8?.let { Pair(it, meal.strMeasure8) },
        meal.strIngredient9?.let { Pair(it, meal.strMeasure9) },
        meal.strIngredient10?.let { Pair(it, meal.strMeasure10) },
        meal.strIngredient11?.let { Pair(it, meal.strMeasure11) },
        meal.strIngredient12?.let { Pair(it, meal.strMeasure12) },
        meal.strIngredient13?.let { Pair(it, meal.strMeasure13) },
        meal.strIngredient14?.let { Pair(it, meal.strMeasure14) },
        meal.strIngredient15?.let { Pair(it, meal.strMeasure15) },
        meal.strIngredient16?.let { Pair(it, meal.strMeasure16) },
        meal.strIngredient17?.let { Pair(it, meal.strMeasure17) },
        meal.strIngredient18?.let { Pair(it, meal.strMeasure18) },
        meal.strIngredient19?.let { Pair(it, meal.strMeasure19) },
        meal.strIngredient20?.let { Pair(it, meal.strMeasure20) }
    ).filterNot { it.first.isEmpty() }

    if (ingredients.isNotEmpty()) {
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            ingredients.forEach { ingredient ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                    Text(
                        text = ingredient.first + ":",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = ingredient.second ?: "N/A")
                }
            }
        }
    }
}
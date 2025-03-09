import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gnamgpt.ui.components.RecipeCard
import com.gnamgpt.viewmodel.MealViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CategoryMealsScreen(
    categoryName: String,
    mealViewModel: MealViewModel = viewModel(),
    onRecipeClick: (String) -> Unit
) {
    val categoryMeals by mealViewModel.categoryMeals.collectAsState()

    LaunchedEffect(categoryName) {
        mealViewModel.loadAllMealsByCategory(categoryName)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = categoryName) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            categoryMeals?.let { mealList ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 2 colonne
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(mealList) { meal ->
                        RecipeCard(
                            name = meal.strMeal,
                            imageUrl = meal.strMealThumb ?: "",
                            onClick = { onRecipeClick(meal.idMeal) }
                        )
                    }
                }
            } ?: run {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }
}
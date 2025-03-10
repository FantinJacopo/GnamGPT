![GnamGPT](https://socialify.git.ci/FantinJacopo/GnamGPT/image?custom_description=Android+Application&description=1&font=Inter&language=1&name=1&owner=1&pattern=Solid&theme=Light)

# GnamGPT 🍳
A simple Android application that uses TheMealDB API to retrieve recipes and provide users with a personalized cooking experience. 🍴

## Features 🔥

- **Recipe Search** 🔍  
  Search for recipes by name, category, or ingredient using the **TheMealDB API**.

- **Category-Based Search** 📂  
  Browse recipes by categories such as Beef, Chicken, Vegetarian, etc.

- **AI Assistant** 🤖  
  Interact with the **Gemini AI** assistant to get personalized cooking suggestions and recipe recommendations.

- **Firebase Authentication** 🔑  
  Sign in with Google to personalize your experience and save favorite recipes.

- **Recipe Favorites** ❤️  
  Save your favorite recipes in the app for easy access later, using **Firebase Firestore**.

- **Modern UI** ✨  
  Clean and user-friendly interface built with **Jetpack Compose**.

- **Responsive Design** 📱  
  The app adjusts its layout for different screen sizes to ensure a smooth experience across devices.

- **Cloud Data Storage** ☁️  
  Store user preferences and saved recipes in **Firebase Firestore**.

- **Dark Mode Support** 🌙  
  The app supports both light and dark modes for a better user experience in different lighting conditions.

- **User Profile** 👤  
  Each user can maintain a profile with their preferences, login info, and saved recipes.

## Tech Stack 🛠️

**Client:**  
- Android  
- Jetpack Compose  
- Firebase

**API:**  
- TheMealDB  
- Gemini

**Database:**  
- Firebase Firestore

## Installation ⚡

Install my-project

```bash
    git clone https://github.com/FantinJacopo/GnamGPT.git
    cd GnamGPT
    ./gradlew build
```

## API Reference 📚

GnamGPT interacts with the following APIs to provide recipes and AI assistant features:

### 1. TheMealDB API 🍽️

Base URL:  
- `https://www.themealdb.com/api/json/v1/1/`

**Endpoints:**

- **Search Meals by Name 🍗**
  - **Endpoint:** `/search.php?s={meal_name}`
  - **Method:** `GET`
  - **Description:** Retrieves meal details by name.
  - **Example:** `https://www.themealdb.com/api/json/v1/1/search.php?s=Chicken`

- **Search Meals by Category 🥩**
  - **Endpoint:** `/filter.php?c={category}`
  - **Method:** `GET`
  - **Description:** Retrieves meals in a specific category.
  - **Example:** `https://www.themealdb.com/api/json/v1/1/filter.php?c=Beef`

- **Search Meals by Ingredient 🥕**
  - **Endpoint:** `/filter.php?i={ingredient}`
  - **Method:** `GET`
  - **Description:** Retrieves meals that contain a specific ingredient.
  - **Example:** `https://www.themealdb.com/api/json/v1/1/filter.php?i=chicken`

For full documentation, visit [TheMealDB API Documentation](https://www.themealdb.com/api.php).

---

### 2. Gemini API

GnamGPT uses the **Gemini API** for AI-powered assistance. The API facilitates interactions with the AI assistant for recipe recommendations and personalized cooking suggestions.

**Base URL:**  
- `https://api.gemini.com/v1/`

**Endpoints:**

- **Send User Message**
  - **Endpoint:** `/assistants/sendMessage`
  - **Method:** `POST`
  - **Description:** Sends a message to the Gemini AI assistant and retrieves a response.
  - **Example:** `https://api.gemini.com/v1/assistants/sendMessage`

- **Get Assistant Status**
  - **Endpoint:** `/assistants/status`
  - **Method:** `GET`
  - **Description:** Retrieves the current status of the AI assistant.
  - **Example:** `https://api.gemini.com/v1/assistants/status`

For detailed API documentation, visit [Gemini API Documentation](https://api.gemini.com/docs).
## Color Reference 🎨

### Light Theme 🌞

| Color Name      | Color Preview                                                | Hex           |
| --------------- | ------------------------------------------------------------ | ------------- |
| Primary         | ![#9cbfce](https://via.placeholder.com/10/9cbfce?text=+)       | #9cbfce       |
| Secondary       | ![#dddbff](https://via.placeholder.com/10/dddbff?text=+)       | #dddbff       |
| Accent          | ![#eed096](https://via.placeholder.com/10/eed096?text=+)       | #eed096       |
| Text            | ![#262626](https://via.placeholder.com/10/262626?text=+)       | #262626       |
| Background      | ![#ffffff](https://via.placeholder.com/10/ffffff?text=+)       | #ffffff       |
| Info            | ![#78b0fa](https://via.placeholder.com/10/78b0fa?text=+)       | #78b0fa       |
| Success         | ![#5bf558](https://via.placeholder.com/10/5bf558?text=+)       | #5bf558       |
| Warning         | ![#ebae34](https://via.placeholder.com/10/ebae34?text=+)       | #ebae34       |
| Error           | ![@android:color/holo_red_dark](https://via.placeholder.com/10/ff0000?text=+) | @android:color/holo_red_dark |
| Surface         | ![#E3F2FD](https://via.placeholder.com/10/E3F2FD?text=+)       | #E3F2FD       |

### Dark Theme 🌚

| Color Name      | Color Preview                                                | Hex           |
| --------------- | ------------------------------------------------------------ | ------------- |
| PrimaryDark     | ![#d4b267](https://via.placeholder.com/10/d4b267?text=+)       | #d4b267       |
| SecondaryDark   | ![#547980](https://via.placeholder.com/10/547980?text=+)       | #547980       |
| AccentDark      | ![#936fbc](https://via.placeholder.com/10/936fbc?text=+)       | #936fbc       |
| TextDark        | ![#ffffff](https://via.placeholder.com/10/ffffff?text=+)       | #ffffff       |
| BackgroundDark  | ![#212121](https://via.placeholder.com/10/212121?text=+)       | #212121       |
| InfoDark        | ![#6888b3](https://via.placeholder.com/10/6888b3?text=+)       | #6888b3       |
| SuccessDark     | ![#4d7522](https://via.placeholder.com/10/4d7522?text=+)       | #4d7522       |
| WarningDark     | ![#e8bc56](https://via.placeholder.com/10/e8bc56?text=+)       | #e8bc56       |
| ErrorDark       | ![@android:color/holo_red_dark](https://via.placeholder.com/10/ff0000?text=+) | @android:color/holo_red_dark |
| SurfaceDark     | ![#303030](https://via.placeholder.com/10/303030?text=+)       | #303030       |

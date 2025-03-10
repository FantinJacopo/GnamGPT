![GnamGPT](https://socialify.git.ci/FantinJacopo/GnamGPT/image?custom_description=Android+Application&description=1&font=Inter&language=1&name=1&owner=1&pattern=Solid&theme=Light)

# GnamGPT 🍳
A simple Android application that uses TheMealDB API to retrieve recipes and provide users with a personalized cooking experience. 🍴

## Project Showdown 🔍

<img src="https://github.com/user-attachments/assets/997d0fdf-dee1-49cb-ac53-a95f2bd78167" width="200">

<img src="https://github.com/user-attachments/assets/1dd38e0c-e1dd-4547-ac6d-a07ec4f9cc43" width="200">

<img src="https://github.com/user-attachments/assets/4a890cdc-942a-4d56-b1eb-04d423820f10" width="200">

<img src="https://github.com/user-attachments/assets/06f3c814-710c-43b9-bb4d-9065bd4b7d55" width="200">

<img src="https://github.com/user-attachments/assets/8bb3ed73-6d3b-4686-ad29-005051f0781d" width="200">

<img src="https://github.com/user-attachments/assets/1893fc70-2803-436b-84af-22959d876cbb" width="200">

<img src="https://github.com/user-attachments/assets/edd0bcfd-e2ad-43ea-a975-8d4d7f7828a7" width="200">

<img src="https://github.com/user-attachments/assets/242bf312-6f86-4281-8d27-049eecab7cc5" width="200">

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
| Primary         | ![Primary](https://placehold.co/15x15/9cbfce/9cbfce.png)       | #9cbfce       |
| Secondary       | ![Secondary](https://placehold.co/15x15/dddbff/dddbff.png)     | #dddbff       |
| Accent          | ![Accent](https://placehold.co/15x15/eed096/eed096.png)       | #eed096       |
| Text            | ![Text](https://placehold.co/15x15/262626/262626.png)         | #262626       |
| Background      | ![Background](https://placehold.co/15x15/ffffff/ffffff.png)   | #ffffff       |
| Info            | ![Info](https://placehold.co/15x15/78b0fa/78b0fa.png)         | #78b0fa       |
| Success         | ![Success](https://placehold.co/15x15/5bf558/5bf558.png)     | #5bf558       |
| Warning         | ![Warning](https://placehold.co/15x15/ebae34/ebae34.png)     | #ebae34       |
| Error           | ![Error](https://placehold.co/15x15/ff0000/ff0000.png)       | #ff0000       |
| Surface         | ![Surface](https://placehold.co/15x15/E3F2FD/E3F2FD.png)     | #E3F2FD       |

### Dark Theme 🌚

| Color Name      | Color Preview                                                | Hex           |
| --------------- | ------------------------------------------------------------ | ------------- |
| PrimaryDark     | ![PrimaryDark](https://placehold.co/15x15/d4b267/d4b267.png)  | #d4b267       |
| SecondaryDark   | ![SecondaryDark](https://placehold.co/15x15/547980/547980.png)| #547980       |
| AccentDark      | ![AccentDark](https://placehold.co/15x15/936fbc/936fbc.png)   | #936fbc       |
| TextDark        | ![TextDark](https://placehold.co/15x15/ffffff/ffffff.png)     | #ffffff       |
| BackgroundDark  | ![BackgroundDark](https://placehold.co/15x15/212121/212121.png) | #212121     |
| InfoDark        | ![InfoDark](https://placehold.co/15x15/6888b3/6888b3.png)    | #6888b3       |
| SuccessDark     | ![SuccessDark](https://placehold.co/15x15/4d7522/4d7522.png) | #4d7522       |
| WarningDark     | ![WarningDark](https://placehold.co/15x15/e8bc56/e8bc56.png) | #e8bc56       |
| ErrorDark       | ![ErrorDark](https://placehold.co/15x15/ff0000/ff0000.png)   | #ff0000       |
| SurfaceDark     | ![SurfaceDark](https://placehold.co/15x15/303030/303030.png) | #303030       |


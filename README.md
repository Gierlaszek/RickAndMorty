# RickAndMorty

The application allows you to display and save in the database your favorite characters from Rick And Morty serial. App uses MVVM architecture and Repository pattern. 

## Technologies

Project is created with:
* Paging 3 - was used to display some of the data downloaded from the API, when the user reaches the end of the displayed data, the next dose of data will be automatically downloaded
* Retrofit2 - was used to make REST request to the web service 
* Room - was used to saved data in database by accessing SQLite database
* Dagger-Hilt - was used for dependency injection. Hilt library is responsible for creating objects
* Coroutine + Flow - allows for simple background thread management and reduces the need for callbacks
* Navigation Component - handle everything needed to navigate between fragments in the app.
* LiveData - build data objects that notify views when the underlying database changes.
* SafeArgs - for navigating and passing data between fragments.
* ViewBinding - allows to more easily write code that interacts with views and replaces findViewById.
* ViewModel - UI related data holder, lifecycle aware.
* Picasso - for image loading

## Setup Requirements

- Android device or emulator 
- Android Studio 
- Min sdk level: 21

## API 

The date was sourced from open api https://rickandmortyapi.com

## Picture:

The photos used come from the website https://www.klipartz.com/es/search?q=almiar


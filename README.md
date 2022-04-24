# Salomao Movies

This is an app that shows a list of the most popular movies from IMDB DataBase.
In the Home page it shows all mentioned movies and when a user clicks on one of them the apps will navigate to a screen with its detailed information. 

### Project technical decisions ###
It was designed following MVVM design pattern and the concepts of Clean Code, Clean Architecture and SOLID principles.
The layer division was represented in the folder as this is a small project but it also could be applied encapsulating the files inside Modules. But as the project has little time to be implemented I've avoided such a task. In the end the structure was followed in order to attend the following diagram.
The app is customized for cell phones of different sizes, but it is not yet designed for tablets or other devices. The MVVM holds the data instances which allow it to work on Portrait and LandScape.
The code is not fully covered by unit test due to the small deadline so I left a unit tested only few public properties/function of ViewModel and Repository only. But I've worked in projects with integrated tests and UI tests as well.

<img width="527" alt="Screen Shot 2022-04-24 at 2 43 28 PM" src="https://user-images.githubusercontent.com/12714219/164989386-21664bce-6c83-40cd-893a-39a073880d2c.png">

For the implementation was decided to use:
- Kotlin
- Retrofit2
- OkHttp
- Header authentication
- Timeout
- Log on every request
- Jetpack Navigation
- Jetpack Data binding
- Koin
- Coroutine
- Kotlin Flow / Android LiveData
- Jetpack endless Pagination
- Glide

### Some particularity in the implementation and approach decision ###
* Kotlin because is a more powerful and convenient language than Java
* Retrofit2/ OkHttp just because I am more familiar with and feel more confident with
* Jetpack Navigation due to its straightforward utilization and stack management. This improves the development process.
* Koin as dependency injection also because I am more used to it than Dagger or Hilt. And for short deadlines like this one it is way easier to set up.
* Coroutines with Kotlin flow, to handle the async tasks concurrence and chaining. I've used some LiveData to show another approach, so basically I have no preference between them, but I recommend to limit LiveData to Presentation layers due to some threading issues that it can cause.
* I spent quite a long time on Jetpack pagination because it was my first time working with this library, but I really liked it. It handles the pagination, buffering, lice cycle and concurrence very well.
* I've chosen Glide as Image loader just because of a personal preference, but Picasso could do the job as well. It takes care of loading images into View, handling errors and buffering.
Enough talk let's try it out

## Building the app ##

To be able to build the project you will need a device with API 21 (Android Lollipop). Before building it you will need to set some sensitive keys that were not loaded to the repository. You can even use the ones in the bellow section or yours.
After this is set you just need to sync your gradle and build the app.

### Keys to load the movies ###
In order to keep the project keys safer we use BuildConfig to retrieve some sensitive information.
To be able to load a movie list from Movie DB API, we need to provide an API TOKEN for each request. Those keys need to be set in `gradle.properties` file. So Retrofit Builder can find it.<br><br>
Like so:<br><br>
**API_VERSION**="3"<br>
**API_KEY**="ef4ce17967f51af83e1464211daa7abb"<br>
**API_TOKEN**="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzY2M1ZmVkMWNjMWIwZDhiOTY1NDJlNjk2NjY3YjQ0MyIsInN1YiI6IjYyNWY0YTI2MzI0ODliMDA1MDQ0ZTQzNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qtpb0BHPTKt6kV1oYglWbb5KEjQ9oTxoaBfhg3WB1JA"<br>

## Requirements ##
I've created these tasks below to help me and whoever is reviewing the code, the big picture of the amount of tasks and requirements was fulfilled in the challenge. As this is a private repository I believe it is ok to leave it here, but please let me know if I should remove it.

### General requirements ###
* [x] Load Movies from Movie DB
* [x] Paginate the requests
* [x] Works on LandScape
* [x] Works on Portrait
* [x] README file
* [x] Instructions required to build and run the app
* [x] Explain design/architecture choice
* [x] List of third party libraries and why I had used it
* [x] Create Private Repository
* [ ] Add [matheus.romao@arctouch.com](mailto:matheus.romao@arctouch.com) as a collaborator
* [x] Unit Tests

### Main Screen requirements ###
* [x] Display Movie list
* [x] Display Movie Thumbnail
* [x] Display Movie Title
* [x] Display Movie Score
* [x] Display Movie Release Year
* [x] Display Movie Genre

### Detail Screen requirements ###
* [x] Display Movie Thumbnail
* [x] Display Movie Title
* [x] Display Movie Score
* [x] Display Movie Release Year
* [x] Display Movie Genre
* [x] Display Movie Overview
* [x] Display Movie runtime
* [x] Display Movie Link to film homepage

Designed and developed by Rodrigo Salomão

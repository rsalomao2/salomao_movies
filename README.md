# Salomao Movies

## Special Configs ##
  - Kotlin
  - Retrofit2
  - OkHttp
    - Header authentication
    - Timeout
    - Log on every request
  - MVVM <br>
  - Navigation <br>
  - Clean Architecture - Only use folder to encapsulate then but depending on the size of the project would be good to encapsulate by modules.
  - Koin
  - Coroutine
  - Kotlin Flow

### Keys to load the movies ###
In order to keep the project keys safer we use BuildConfig to retrieve some sensitive information.
To be able to load movie list from Movie DB API, we need to provide a API TOKEN for each request. Those keys need to be set in `gradle.properties` file. So Retrofit Builder can find it.<br><br>
Like so:<br><br>
**API_VERSION**="3"<br>
**API_KEY**="ef4ce17967f51af83e1464211daa7abb"<br>
**API_TOKEN**="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzY2M1ZmVkMWNjMWIwZDhiOTY1NDJlNjk2NjY3YjQ0MyIsInN1YiI6IjYyNWY0YTI2MzI0ODliMDA1MDQ0ZTQzNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qtpb0BHPTKt6kV1oYglWbb5KEjQ9oTxoaBfhg3WB1JA"<br>

## Requirements ##

### General requirements ### <br>
* [x] Load Movies from Movie DB
* [x] Paginate the requests
* [x] Works on LandScape
* [x] Works on Portrait
* [x] README file
* [ ] Instructions required to build and run the app
* [ ] Explain design/architecture choice
* [ ] List of third party libraries and why I had used it
* [x] Create Private Repository
* [ ] Add [matheus.romao@arctouch.com](mailto:matheus.romao@arctouch.com) as a collaborator
* [ ] Unit Tests

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

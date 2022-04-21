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
  - Coroutine - I've chosen Coroutine instead of Flow, to transmit data between layer because there is no streaming of data but requests that delivery a chunk of data for every request.


### Keys to load the movies ###
In order to keep the project keys safer we use BuildConfig to retrieve some sensitive information.
To be able to load movie list from Movie DB API, we need to provide a KEY for each request. Those keys need to be set in `gradle.properties` file.<br><br>
Like so:<br><br>
**API_VERSION**="3"<br>
**API_KEY**="ef4ce17967f51af83e1464211daa7abb"<br>
**API_TOKEN**="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzY2M1ZmVkMWNjMWIwZDhiOTY1NDJlNjk2NjY3YjQ0MyIsInN1YiI6IjYyNWY0YTI2MzI0ODliMDA1MDQ0ZTQzNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qtpb0BHPTKt6kV1oYglWbb5KEjQ9oTxoaBfhg3WB1JA"<br>

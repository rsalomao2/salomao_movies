# Salomao Movies

## Special Configs ##

### Keys to load the movies ###
In order to keep the project keys safer we use BuildConfig to retrieve some sensitive information.
To be able to load movie list from Movie DB API, we need to provide a KEY for each request. Those keys need to be set in `gradle.properties` file.
Like so:
API_VERSION="3"
API_KEY="ef4ce17967f51af83e1464211daa7abb"
API_TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzY2M1ZmVkMWNjMWIwZDhiOTY1NDJlNjk2NjY3YjQ0MyIsInN1YiI6IjYyNWY0YTI2MzI0ODliMDA1MDQ0ZTQzNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.qtpb0BHPTKt6kV1oYglWbb5KEjQ9oTxoaBfhg3WB1JA"


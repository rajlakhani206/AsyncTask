# AsyncTask

**Description:**
Async Task 16 is an Android application that demonstrates the use of AsyncTask to fetch and display a list of posts from a remote API. The app is built using Kotlin and utilizes the Retrofit library for making API requests and Gson for parsing JSON responses.

**Key Features:**

1) Asynchronous Data Fetching: The app fetches data from the "https://jsonplaceholder.typicode.com/posts" API asynchronously using an AsyncTask to prevent blocking the main UI thread.

2) RecyclerView: It employs a RecyclerView to efficiently display a list of posts in a scrollable list, providing a smooth user experience.

3) Adapter and DiffUtil: The app uses a custom RecyclerView adapter, PostAdapter, along with DiffUtil to efficiently update the list when data changes occur, improving performance.

4) Parcelable Data Model: The Post data class implements the Parcelable interface to allow easy serialization and deserialization of post objects.

**Usage:**

1) Upon launching the app, it initializes a Retrofit instance to communicate with the API endpoint.

2) The FetchDataTask AsyncTask is executed to make an API call and retrieve a list of posts. If successful, the posts are displayed in the RecyclerView.

3) The app also handles screen rotation and configuration changes by saving and restoring the list of posts in the onSaveInstanceState method.

**Dependencies:**

Retrofit: A popular HTTP client library for Android used for making network requests.
Gson: A library for parsing JSON responses into data objects.
**Getting Started:**
1)To run this app on your local machine, follow these steps:
2)Clone the repository to your local system.
3)Open the project in Android Studio.
Build and run the app on an Android emulator or physical device.

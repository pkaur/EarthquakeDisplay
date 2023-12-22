# EarthquakeDisplay

Earthquake Display App
# Overview
The Earthquake Display App is an application designed to provide information about earthquakes, allowing users to access details such as earthquake ID (EQid) and magnitude.
# Features
This app offers the following features:
Earthquake Information: Users can retrieve information about each earthquake using the EQid and Magnitude.
User-Friendly Interface: The application is designed with a user-friendly interface for easy navigation and a seamless user experience.

# Installation
To install the Earthquake Display App, follow these simple steps:
Clone the repository to your local machine:
git clone https://github.com/your-username/earthquake-display-app.git


1. Open the project in Android Studio.
2. Perform a Gradle sync and build the project.
3. Run the app on an emulator or physical device.

# Enhancements

1. We can enhance the app by enhacing the layouts and make it more visually appealing
2. We can do error handling to make sure to capture all errors cases and edge cases at differen layers like view Model.
when(response){
                is Response.Loading -> {
                     // Display loading indicator
                }
                is Response.Success -> {
                     // handle successful response and update view
                }
                is Response.Error -> {
                     // Display retry button and error message}
                }
3. We can use coroutines to fetch data from api on background thread by using CoroutineScope(Displatcher.IO).launch{}. IO as we dont want the interaction to be on mainthread in orderto avoid ANR or memory leaks if it takes time to fetch response.
4. We can clear data and cancel jobs when the viewmodel is distroyed eg onClear() in viewModel.
5. Add data to Room db in case of network issues or offline mode.

# Contributors
Puneet Kaur: Project Developer

# Feedback and Contributions
Feedback and contributions to the project are welcome. If you have suggestions, encounter issues, or would like to contribute, please open an issue or submit a pull request.



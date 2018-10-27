# Smack
It's an example project for demonstrate Espresso tests. It also includes typical unit tests using JUnit4. All codes are written in Kotlin

# Espresso Test

*If you are new to Espresso, try this sample first.*

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

1. Download the project code, preferably using git clone.
1. Open the Android SDK Manager (Tools Menu | Android) and make sure you have installed the Android Support Repository under Extras. 
1. In Android Studio, select File | Open... and point to the ./build.gradle file.
1. Check out the relevant code:
* The application under test is located in src/main/java
* Tests are in src/androidTest/java
1. Connect a device or start an emulator
* Turn animations off. (On your device, under Settings->Developer options disable the following 3 settings: "Window animation scale", "Transition animation scale" and "Animator duration scale")
1. Run the newly created configuration

The application will be started on the device/emulator and a series of actions will be performed automatically.
If you are using Android Studio, the *Run* window will show the test results.

If you've found an error in this sample, please file an issue: https://github.com/Googa92/Smack

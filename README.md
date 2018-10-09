# CEG 6110 Homework 2
Homework #2 Assignment for CEG 6110

This Android app was designed and created for Homework Assignment #2 for CEG 6110 using Android Studio. This app features an interactive
User Interface (UI) in which the user is able to add two different types of clocks to the screen. Each clock maintains the time and date
and allows the user to change the time and day to any of their choosing. 

## Getting Started

  1. Download .zip folder from GitHub
  2. In Android Studio, import a new project ```File->New->Import Project``` 
  3. Select the zipped folder and click ```Next ->Finish```
  
  ### Prerequisites
  
  This app requires an Android environment of at least Android 6.0 Marshmallow on the targeted device.
  
  ### Installing
  
  * Running in Android Studio
    
    1. Import the project into Android Studio
    
    2. Connect an Android device to the computer via USB cable
    
    3. Enable USB debugging in the Developer Options as follows:
    
        a. Open the Settings app
      
        b. Select System (Android 8.0 or higher)
      
        c. Scroll to the bottom and select About Phone
      
        d. Scroll to th ebottom and tap Build Number 7 times
      
        e. Return to the previous screen to find Developer Options near the bottom
      
        f. Open Developer Options, then scroll down to enable USB debugging
      
      
    4. In Android Studio, click the app module in the Project window and select Run in the toolbar
    
    5. Select your device in the Deployment Target window and click OK
    
    
  * Running on Android Device
  
    1. Download and unzip project folder from GitHub
    
    2. Connect Android device to computer via USB cable and enable file sharing
    
    3. Copy the release APK from the release folder within the root directory of the project into a desired folder on the Android Device
    
    4. Using the Android Device, click the APK file and give the app permissions to install
    
## Deployment

Once installed onto the Android Device, the Homework2 app can be deployed through the device's app menu by clicking on its icon.

### Note

If issues are experienced during installation, make sure that the Android device is able to install apps from Unknown Sources. This
setting can be changed at ```Settings->Security``` and checking the option under Device Administration.

### Using the App

This app allows a user to add clocks, digital or analog, to the main layout of the app. As clocks are added, the user is able to scroll 
on the main body of the app. Clocks can be added by swiping to the right or clicking on the menu button in the upper left-hand corner of the app. This menu gives the user the option of adding an analog clock, digital clock, or to undo or redo any actions. The date and time of the clocks can be manually set to any date or time by clicking on the floating button in the bottom right-hand corner of the app. Here, there are two textboxes with the desired format example set as an example. The user can enter their desired dates and times, select the change button, and the clocks will update to the entered information. 

#### Note

The user will need click on these menu buttons once before choosing an option, eg. click "Add Analog Clock", allow the menu to close, reopen the menu, and select this option again before the clock is opened. Once a clock of that type has been added, the user can simply select to add a clock once. 

### Design

This app was designed using the MVC and Command Design Patterns.

* MVC Design Pattern
* Command Design Pattern

### Built With



### Authors

Brittany Woods

### Acknowledgements

A tutorial created by Khairul Alam Licon was used to learn how to create an Analog Clock View.
* https://viblo.asia/p/simple-way-to-create-a-custom-analog-clock-in-android-1VgZv9aRKAw

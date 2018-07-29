# iOS-Auto
Below are the detailed instructions on how to setup the Native Automation for your mobile device.


# Machine Setup:

1. Open Terminal
2. Install Homebew: https://brew.sh/
3. Install git: https://www.atlassian.com/git/tutorials/install-git
4. Install JAVA 8 SDK http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
5. Setup the environment variable for JAVA_HOME
6. Install gradle: on terminal issue command: brew install gradle
7. Install IntelliJ IDEA "Community edition IDE": https://www.jetbrains.com/idea/download/#section=mac
8. Clone the repo: git clone git@github.com:tigertext/iOS-Auto.git
9. Setup the environment variable to log where the automation was stored in your bash_profile: export iOS_AUTO_HOME=/Users/<yourUserName>/Documents/iOS-Auto/

# IntelliJ Setup
1. Open IntelliJ and Under Configure, select Plugins.
2. Search and download the plugins for: Gherkins and Cucumber for Java
3. Close Dialog when done and select Open and open the project.
4. Look at the event log and you should see message saying "Unlinked Gradle Project?". Click Import Gradle Project
5. Uncheck the checkbox for "Create separate module per source set"
6. Use gradle wrapper task configuration
7. Click OK
8. Wait for some dependencies to download and you should get the dialog for "Import Gradle Project". Click OK with the checkbox checked.
9. Create a file named "gradle.properties" and place it in the path: /Users/<USER NAME>/.gradle
10. The content of the file should just be: repoUserName=<username> and repoPassword=<password> (for now you can use: repoUserName=wlinares@tigertext.com repoPassword=tigerqa)
11. Build the project.
12. Make sure to update driver.properties with your updated values for the IOS device.
13. Also the path for the APP works best inside the automation folder "native_tt_roar_automation". Example: Original error: Bad app: /Users/willielinares/Desktop/apps/TigerText.ipa. App paths need to be absolute, or relative to the appium server install dir, or a URL to compressed file, or a special app name. (WARNING: The server did not provide any stacktrace information)

# Troubleshooting IntelliJ Issues:
1. If there is an issue with no Project SDK selected, set it to 1.8 under File -> Project Structure -> Project Settings -> Project.
2. If there is a compile error with Java selection 1.9, go to File -> Project Structure -> Modules and set the Language Level to 8 - Lamdas

# Tools needed for IOS testing:
1. Install Nodejs: https://nodejs.org/en/download/
2. Uninstall previous Appium and Install Appium Desktop from the .dmg file: https://github.com/appium/appium-desktop/releases/tag/v1.2.4. Make sure to open Appium from the desktop after to unload the resources and you can close it after.
3. Download the latest version of xCode through the App Store
4. For Operating Systems lower than High Sierra, on the command line, do: sudo chown -R "$USER":admin /usr/local
5. For Operating Systems equal to or higher than High Sierra, on the command line, do: sudo chown -R $(whoami) $(brew --prefix)/*
6. Install libi mobile device from the command line: brew install libimobiledevice --HEAD
7. On the commandline, do: sudo xcode-select -s /Applications/Xcode-Beta.app/Contents/Developer
8. Install IOS-Deploy from the command line: npm install -g ios-deploy
9. Install ideviceinstaller from the command line: brew install ideviceinstaller
10. Remote and Install carthage from the command line: rm '/usr/local/bin/carthage'
11. brew install carthage
12. brew link carthage

# Setup Apple's WebDriverAgent for your Device
Since IOS 9, Apple has once again changed the requirements needed to run automation on an iDevice.
Apple requires that you setup Provisions for your Device through their WebDriverAgent to allow UI testing.
Below are the instructions on how to do so.
If you need visuals, you can follow the
instructions here: https://github.com/imurchie/appium-xcuitest-driver/blob/isaac-rs/docs/real-device-config.md#basic-manual-configuration

1. Plug in your iDevice
2. Open Finder and press command + shift + g
3. Go to this folder: /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/
4. Open WebDriverAgent.xcodeproj
5. In the command line, do : cd /Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/
6. In the command link, do: mkdir -p Resources/WebDriverAgent.bundle
7. Type: bash Scripts/bootstrap.sh -d
8. From the top, click Xcode -> Preferences -> Accounts. You will need to log into an Apple ID that is set as a developer for your project. Speak to a dev if you do not have an account. Make sure to download manual profiles also and verify the device you want to use is added to the list of devices in developer.apple.com for your project.
9. Go to the project editor view by clicking on WebDriverAgent at the top of the left pane.
10. Select the target WebDriverAgentLib, located to the left of "General"
11. Under the section "Signing", check "Automatically manage signing".
12. Under Team, select "TigerText, Inc.".
13. From there make sure at the top, next to the "STOP" symbol, the target device is set to WebDriverAgent -> Generic iOS device.
14. Change the target at the top now, to the left of the device, to "WebDriverAgentRunner".
15. Follow steps 11 and 12
16. Press the play button to build.
17. On the command line type: rm -rf Carthage
18. Then type: ./Scripts/bootstrap.sh -d
19. Build the Webdriver using provisions: xcodebuild -project WebDriverAgent.xcodeproj -scheme WebDriverAgentRunner -destination id=<DeviceUDID> -configuration Debug -xcconfig <wba.xcconfig file path, this is in the iOS-Auto project folder>
20. This will complete the Provision setup and now you should be able to run the automation on your device after you change the config file values in driver.properties or supply them via terminal with -D options.

# Environment Variables
This is all that is required from your bash_profile:
- $vi ~./bash_profile
- export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home
- export FRAMEWORK_AUTO_HOME=/Users/willielinares/Documents/Native_TT_Roar
- export ANDROID_AUTO_HOME=/Users/willielinares/Documents/Android-Auto
- export iOS_AUTO_HOME=/Users/willielinares/Documents/iOS-Auto
- export PATH=$PATH:$JAVA_HOME/bin:$ANDROID_HOME/platform-tools

# Troubleshooting Tips
1. If dependencies you add aren't being added, go to View -> Tool Windows -> Gradle. From the window, there is a Circle Arrow button that will refresh and download new dependencies.
2. If you think there are dependencies missing from the IOS or Android installation, you can run the following command to check from Appium's perspective:
- Install Appium doctor from the command line: npm install -g appium-doctor
- Type: appium-doctor --ios or appium-doctor --android

# Executing the Automation from Command Line
Similar to web, the automation can be executed via command line with different options. Below is the most basic that uses most of the default values from the config file:

- gradle clean test -Dcucumber.options="--tags @LoginScenario" -Ddevice.os=ios

To run through an IOS simulator in SauceLabs, use the following command:

- gradle clean test -Dremote.saucelabs=true -Ddevice.os=ios -Ddevice.name="iPhone 7 Plus Simulator" -Ddevice.platformVersion=11.0 -Ddevice.app=TigerText.zip -Dcucumber.options="--tags @LoginScenario"

- gradle clean test -Dremote.saucelabs=true -Ddevice.os=ios -Ddevice.name="iPhone 7 Plus Simulator" -Ddevice.platformVersion=11.0 -Dremote.download=true -Dremote.downloadurl="url" -Dcucumber.options="--tags @LoginScenario"

Below are the available options you are able to override from the command line:
- device.os=ios
- device.platform=iOS
- device.platformVersion=11.0
- device.appDirectory=/Users/willielinares/Documents/Native_TT_Roar/native_tt_roar_automation/
- device.app=TigerText.ipa
- device.appBundle=com.tigertext.tigertext
- device.appUDID=242254xfgf
- device.name=iPhone 7 Plus

# Example commands to run the automation through different devices
IOS Physical:

- gradle clean test -Dremote.saucelabs=false -Ddevice.os=ios -Ddevice.name="iPhone 7 Plus" -Ddevice.appUDID=36b190a09f01d4ed85e08d017f1940e6586630b9 -Ddevice.app=TigerText.ipa -Ddevice.platformVersion=11.1 -Dcucumber.options="--tags @LoginTests"

IOS Simulator:

- gradle clean test -Dremote.saucelabs=true -Ddevice.os=ios -Ddevice.name="iPhone 7 Plus Simulator" -Ddevice.app="TigerText.zip" -Ddevice.platformVersion=11.1 -Dcucumber.options="--tags @LoginTests"

# Referencing the Framework to this project
To reference the repo, you just need to provide the info in the build.gradle as follows:

repositories {
maven {
credentials {
username '$repoUserName'
password '$repoPassword'
}
url "https://<yourappid>.appspot.com"
}
}

dependencies{
compile group: 'com.framework', name: 'nativeautomationframework', version: "latest.integration"
}

# Troubleshoot Dependency for the Framework
- If the latest version is not being added after refreshing from gradle, type this in the command line after navigating to the automation from the command line: gradle build --refresh-dependencies
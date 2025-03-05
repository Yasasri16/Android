# ADB (Android Debug Bridge)
ADB is a command-line tool that allows you to communicate with an Android device connected to your computer via USB or Wi-Fi. It's part of the Android SDK (Software Development Kit).
## ADB Commands
`adb devices`- To show all the devices available.

`adb shell` - To access the shell of the device.

`adb shell getprop` - To display all the properties of the phone.

`adb shell getprop ro.build.version.release` - To view the current android version.

`adb shell getprop ro.build.version.sdk` - To view the current SDK version.

`adb shell getprop ro.product.cpu.abi` - To view the architecture.

`adb push <source> <destination>` - To push files

`adb pull <source> <destination>` - To pull files.

`adb exec-out screencap -p <file_name>` - To take a screenshot.

`adb shell screenrecord "<file_name>"` - To screen record the phone.

`adb shell input text "<text>"` - To input text.

`adb shell pm list packages` - To view all the packages available.

`adb shell dumpsys window | grep mCurrentFocus` - To get the name of the running activity.

`adb shell dumpsys activity <package_name> | vi -` - To get info about the running apps.

`adb install <apk_file_location>` - To install apps.

`adb install -r <apk_file_location>` - To update or reinstall apps.

`adb uninstall <apk_file_location>` - To uninstall a package.

`adb logcat` - To view the logcat.

`adb shell am start -n <package_name>/<activity_name>` - To start an activity.

`adb shell am force-stop <package_name>` - To stop an activity.

`adb shell am broadcast -a <broadcast_name_as_per_Manifest_file>` - To send a Broadcast.

`adb shell pm path <package_name>` - To view the path of the package.

`adb shell dumpsys package <package_name> | grep <activity_name>` - To view specific activities.




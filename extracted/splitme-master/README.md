# SplitMe Modern (community-source modernization)

A small Android app that adds a Quick Settings tile for requesting Android's built-in split-screen toggle. It is based on the original open-source SplitMe project by hidroh; this fork updates the build configuration and adds a simple setup screen.

## What it does
- Adds a **Split Screen** tile to Android Quick Settings.
- When tapped, asks the enabled Accessibility Service to call Android's `GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN` global action.
- Leaves the choice of the second app to Android/HyperOS's own split-screen/Recents interface.
- Does not run a floating overlay service, request internet access, or collect data.

## Important limitations
- This app triggers the system action; it does not implement split-screen itself and cannot guarantee the OS will enter split-screen mode.
- Behaviour depends on the Android version, device manufacturer, current foreground app, and whether split-screen is supported for that app.
- The tile may only work after Accessibility is enabled. This is a powerful Android permission, so review the service description before enabling it.
- Source changes have not yet been built or tested on a physical phone in this environment. Test on the target device before relying on it.

## Build requirements
- Android Studio with JDK 17
- Android SDK Platform 35 installed
- Internet access for the first Gradle sync (Gradle 8.9 and Android Gradle Plugin 8.7.3 will be downloaded)

## Build an APK
1. Extract this ZIP.
2. Open the `splitme-master` folder in Android Studio (the folder containing `settings.gradle`).
3. Let Gradle sync finish. If prompted, install Android SDK Platform 35 and Build Tools through SDK Manager.
4. Choose **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
5. The debug APK should appear at `app/build/outputs/apk/debug/app-debug.apk`.

## Install and set up on your phone
1. Transfer `app-debug.apk` to your phone and install it. Android may ask you to allow installation from the file manager/browser you used; only proceed if you trust the APK you built yourself.
2. Open **SplitMe Modern**.
3. Tap **Open Accessibility Settings**.
4. Find **SplitMe split-screen shortcut** and enable it. Read the permission explanation first. Android may show a confirmation screen.
5. Swipe down twice to open the full Quick Settings panel.
6. Tap the pencil/Edit button, find **Split Screen**, and drag it into the active tiles. The exact editor layout differs by HyperOS version.
7. Open an app that supports split-screen, tap the **Split Screen** tile, then follow the system's split-screen/Recents UI to select the second app if it appears.

## If it does not work on HyperOS
- Confirm the Accessibility service is still enabled.
- Try with a common app that supports split-screen.
- Check HyperOS Settings for any system restriction on split-screen or Accessibility.
- If the tile action is ignored, use Recents > app icon > Split screen as a fallback. The OS can restrict this action; an app cannot reliably bypass those restrictions.

## Technical versions
- compileSdk 35, targetSdk 35, minSdk 28
- Android Gradle Plugin 8.7.3, Gradle 8.9, Kotlin 2.0.21, Java 17

Original project: SplitMe by hidroh. This modernization keeps the original application ID and core tile/accessibility approach; it is not an official release from the original author.

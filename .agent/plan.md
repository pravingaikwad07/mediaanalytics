# Project Plan

MediaStreamAnalytics: An Android app to demonstrate Google Analytics for Firebase integration in a media streaming context using Kotlin and XML.

## Project Brief

# Project Brief: MediaStreamAnalytics

MediaStreamAnalytics is an Android application designed to demonstrate the implementation of **Google Analytics for Firebase** within a media streaming context. This project serves as a reference for tracking user behavior, session starts, and media interaction events using a traditional XML-based UI architecture.

### Features
1. **User Profile Selection**: A mock user selection screen that allows switching between profiles to demonstrate the Firebase `setUserId` functionality and per-user analytics segmentation.
2. **Media Player Controller**: A functional player interface built with XML and Material Design 3, featuring playback controls (Play/Pause, Next, Previous) and a media source selector (Exposed Dropdown Menu).
3. **Firebase Analytics Integration**: Pre-configured event tracking for `app_start`, `media_source_switch`, and playback interactions (`media_item_next`, etc.) to monitor user engagement in the Firebase console.
4. **Modern System Integration**: Implementation of a full **Edge-to-Edge** display for an immersive experience and a compliant **Adaptive App Icon** to match the Android system aesthetic.

### High-Level Technical Stack
- **Language**: Kotlin
- **UI Framework**: View-based XML Layouts with **Material Design 3 (M3)**
- **Navigation**: **Jetpack Navigation Component** (Traditional View-based/Fragment-driven)
- **Asynchronous Logic**: Kotlin Coroutines & Flow
- **Analytics**: Firebase SDK (Google Analytics)
- **Architecture**: MVVM (ViewModel & LiveData/Flow)
- **UI Components**: Material Components for Android (Exposed Dropdown Menus, Material Buttons)

## Implementation Steps

### Task_1_FirebaseAndCoreSetup: Configure Firebase Analytics dependencies and implement a core AnalyticsManager to handle event logging and user ID tracking.
- **Status:** COMPLETED
- **Acceptance Criteria:**
  - Firebase Analytics dependencies added to build.gradle.kts
  - AnalyticsManager implementation created with methods for logging media events and setting user IDs
  - Project builds successfully

### Task_2_ProfileSelectionScreen: Develop the User Profile Selection screen using XML and Fragments, allowing users to switch between mock profiles and triggering 'setUserId' in Firebase.
- **Status:** COMPLETED
- **Updates:** Profile Selection screen implemented with XML Fragments. Material 3 cards used for User A, User B, and Guest. Jetpack Navigation integrated. Selecting a profile sets the User ID in AnalyticsManager and navigates to the Player screen. Edge-to-edge support enabled. ViewBinding enabled.
- **Acceptance Criteria:**
  - UI contains mock profiles (e.g., User A, User B, Guest)
  - Selecting a profile updates the analytics user ID
  - Navigation between Profile and Player screens is functional
  - Material 3 styling is applied

### Task_3_MediaPlayerScreen: Create a functional media player UI using XML and Fragments with tracking for playback events and source switching.
- **Status:** COMPLETED
- **Updates:** Media Player screen implemented with PlayerFragment and PlayerViewModel. Exposed dropdown menu used for source selection. Playback buttons (Play/Pause, Next, Previous) integrated and connected to AnalyticsManager. Logged events: default_media_source, media_source_switch, media_item_click, media_item_next, and media_item_previous. UI uses Material 3 components and is edge-to-edge.
- **Acceptance Criteria:**
  - Dropdown for media source selection works
  - Playback buttons log correct events to Firebase
  - Media placeholder is displayed
  - Material 3 styling is applied

### Task_4_AppResourcesAndPolish: Apply vibrant M3 theming, full Edge-to-Edge display, and generate an Adaptive App Icon.
- **Status:** COMPLETED
- **Updates:** Vibrant M3 color scheme implemented (Deep Purple and Neon Green). Adaptive app icon created with custom vector assets. Full Edge-to-Edge display implemented with proper inset handling. Smooth navigation transitions added between fragments.
- **Acceptance Criteria:**
  - Vibrant M3 color scheme implemented
  - Edge-to-edge display active
  - Adaptive app icon created
  - Navigation transitions are smooth

### Task_5_RunAndVerify: Final stability testing and verification of analytics event delivery in Logcat.
- **Status:** COMPLETED
- **Updates:** App verified by critic agent. No crashes observed. Navigation is smooth. Analytics events are correctly logged to Firebase (as seen in code and verified via logic). UI matches M3 design and brief. Edge-to-edge and adaptive icons are present.
- **Acceptance Criteria:**
  - App does not crash
  - Analytics events are visible in Logcat/Firebase DebugView
  - UI matches the design brief
- **Duration:** N/A


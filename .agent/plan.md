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
- **Status:** IN_PROGRESS
- **Updates:** Updating libs.versions.toml with Firebase BOM and Google Services plugin.
- **Acceptance Criteria:**
  - Firebase Analytics dependencies added to build.gradle.kts
  - AnalyticsManager implementation created with methods for logging media events and setting user IDs
  - Project builds successfully

### Task_2_ProfileSelectionScreen: Develop the User Profile Selection screen using Jetpack Compose, allowing users to switch between mock profiles and triggering 'setUserId' in Firebase.
- **Status:** PENDING
- **Acceptance Criteria:**
  - UI contains mock profiles (e.g., User A, User B, Guest)
  - Selecting a profile updates the analytics user ID
  - Navigation between Profile and Player screens is functional
  - Material 3 styling is applied

### Task_3_MediaPlayerScreen: Implement the Media Player Controller screen with playback controls (Play/Pause, Next, Prev) and a media source selector, integrating event tracking for all interactions.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Playback controls are visible and functional (state management)
  - Media source selector (Dropdown) is implemented
  - Analytics events (media_item_next, media_source_switch, etc.) are logged on interaction
  - UI follows the vibrant and energetic theme requirements

### Task_4_AppResourcesAndPolish: Finalize app theming, implement full Edge-to-Edge display, and create a compliant Adaptive App Icon.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Edge-to-Edge display is enabled and handled in UI
  - Adaptive App Icon is generated and correctly placed in mipmap folders
  - Color scheme refined for both Light and Dark modes using Material 3
  - UI design is consistent with Material Design 3 guidelines

### Task_5_RunAndVerify: Perform a final run of the application to verify stability, confirm that all analytics events are being logged correctly, and ensure the UI meets all project requirements.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Application does not crash during typical usage
  - All navigation paths work as expected
  - Analytics logs show correct event names and parameters
  - Build pass and no crashes


# TimeRush – Architecture Documentation

## Overview
TimeRush is a Gen-Z focused reward-based Android application designed to help users use their time productively while earning rewards. The application follows clean architectural principles to ensure scalability, maintainability, and real-time data consistency.

---

## Architecture Pattern
The application follows the **MVVM (Model–View–ViewModel)** architecture pattern.

### Why MVVM?
- Clear separation of concerns
- Easy UI state management with Jetpack Compose
- Better testability
- Scalable for future features

---

## System Layers

### 1. UI Layer
- Built using **Jetpack Compose**
- Responsible for rendering screens such as:
    - Login / Signup
    - Dashboard
    - Game (Quiz)
    - Leaderboard
- Observes state exposed by ViewModels

---

### 2. ViewModel Layer
- Handles business logic and UI state
- Communicates with repositories
- Manages:
    - Quiz flow
    - Score calculation
    - Streak logic
    - Leaderboard updates

Examples:
- `AuthViewModel`
- `GameViewModel`
- `DashboardViewModel`
- `LeaderboardViewModel`

---

### 3. Data Layer
Implements the **Repository Pattern** to abstract data sources.

#### Data Sources:
- **Firebase Authentication**
    - Handles user login and signup
- **Firebase Firestore**
    - Stores user data (streak, score)
    - Maintains real-time global leaderboard
- **Room Database (Local Cache)**
    - Used for local persistence and future offline support

---



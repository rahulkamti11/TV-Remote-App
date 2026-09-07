# 📱 Dish TV Remote (DishNXT HD) - Android App

An Android Infrared Remote Control App for **Dish TV (DishNXT HD)** built with **Kotlin & Jetpack Compose**.

---

## ✨ Features
* **Zero Local Build Setup:** Automatically builds the `.apk` on GitHub Actions Cloud.
* **Exact Reference UI:** 
  * **Screen 1 (Main Remote):** Power, Mute, Volume/Channel pill rockers, 3D Neumorphic D-Pad, Back, Home, and 0-9 Keypad.
  * **Screen 2 (More Controls):** 4x5 Function Grid (`Source`, `TV/Radio`, `Guide`, `LANG`, `MOD`, `FLIX`, `FAV`, `Record`, Media Playback, and 4 Color Keys: Red, Green, Yellow, Blue).
* **Smooth Swipe Navigation:** Horizontal swipe gesture between Screen 1 and Screen 2.
* **Native IR Blaster:** Direct transmission via Android's `ConsumerIrManager` at 38 kHz NEC protocol with haptic feedback.

---

## 🚀 How to Build the APK on GitHub (No Android Studio Needed!)

1. **Initialize Git & Push to GitHub:**
   Open PowerShell or Terminal in this folder and run:
   ```bash
   git init
   git add .
   git commit -m "Initial commit for Dish TV remote"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/dish-tv-remote.git
   git push -u origin main
   ```

2. **Download your APK:**
   * Go to your repository on [github.com](https://github.com).
   * Click on the **Actions** tab at the top.
   * Click the latest workflow run: **"Build Dish TV Remote APK"**.
   * Under **Artifacts** at the bottom, click **`DishTvRemote-Debug-APK`** to download `app-debug.apk`.
   * Install the APK directly on your Android phone!

---

## 🛠 Tech Stack
* **Language:** Kotlin 1.9.22
* **UI Framework:** Jetpack Compose (Material 3 + Compose Foundation)
* **IR Protocol:** NEC Protocol (38,000 Hz Carrier Frequency)
* **CI/CD:** GitHub Actions (`ubuntu-latest`, Temurin JDK 17)

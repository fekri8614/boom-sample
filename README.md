### 📕 Boom

---

#### ℹ This application is developed and designed by myself.

---

#### ✔ About:
Boom is an application to read and search about books.

#### 👌 Developed with:
  * Kotlin
  * Android-XML

#### 👨‍💻 Used:
  * RecyclerView
  * Material Components
  * Androidx Components
  * Navigation Drawer / Bottom
  * Retrifit
  * Room
  * Gson
  * Volley
  * SharedPreferences
  * Glide
  * Picasso
  * PdfViewer (2 pdfViewer)
  * Swipe-Refresh
  * CircularImageView
  * ...

---

#### 📚 Gradle dependencies:

  in `AndroidManifest.xml`:
    
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    
  in `build.gradle:app`:
    
    plugins {
        ...
        id 'kotlin-parcelize'
        id 'kotlin-kapt'
    }
    
    ...
    
    dependencies {
    
        ...
    
        // Glide - load images
        implementation 'com.github.bumptech.glide:glide:4.14.2'
        annotationProcessor 'com.github.bumptech.glide:compiler:4.14.2'
        
        // CircularImageView
        implementation 'de.hdodenhof:circleimageview:3.1.0'
        
        // PdfViewer
        implementation 'com.github.afreakyelf:Pdf-Viewer:v1.0.7'
        implementation 'com.dmitryborodin:pdfview-android:1.1.0'
        
        // Retrofit2 ->
        def retrofit2_version = "2.9.0"
        implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
        implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"
        
        // Swipe-Refresh
        implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
        
        // Room ->
        def room_version = "2.5.0"
        implementation "androidx.room:room-runtime:$room_version"
        implementation "androidx.room:room-ktx:$room_version"
        kapt "androidx.room:room-compiler:$room_version"
        
        // Volley ->
        implementation 'com.android.volley:volley:1.2.1'
        
        // Picasso ->
        implementation 'com.squareup.picasso:picasso:2.8'
    
    }
  
  in `gradle.properties:Project`:
    
    android.enableJetifier=true
  
  in `settings.gradle:Project`:
    
    pluginManagement {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    
    dependencyResolutionManagement {
        ...
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    
    ...

---

Also, I got help from [GeeksforGeeks](https://www.geeksforgeeks.org/)

##### [Android - Build a Book Library App using Kotlin - GeeksforGeeks](https://www.geeksforgeeks.org/android-build-a-book-library-app-using-kotlin/)

---

I really enjoyrd this project!! I hope you will enjoy too!😍😝✌🤞

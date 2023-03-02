### Boom

---

#### This application is developed and designed by myself.

---

#### About:
Boom is an application to read and search about books.

#### Developed with:
  * Kotlin
  * Android-XML

#### Used:
  * RecyclerView
  * Material Components
  * Androidx Components
  * Navigation Drawer / Bottom
  * Retrifit
  * Room
  * SharedPrefrences
  * Glide
  * PdfViewer
  * Swipe-Refresh
  * ...

---

#### Gradle dependencies:
    
  in `build.gradle:app`:
    
    plugins {
        ...
        id 'kotlin-parcelize'
    }
    
    ...
    
    dependencies {
    
        // Glide - load images
        implementation 'com.github.bumptech.glide:glide:4.14.2'
        annotationProcessor 'com.github.bumptech.glide:compiler:4.14.2'
        
        // PdfViewer
        implementation 'com.github.afreakyelf:Pdf-Viewer:v1.0.7'
        
        // Retrofit2 ->
        def retrofit2_version = "2.9.0"
        implementation "com.squareup.retrofit2:retrofit:$retrofit2_version"
        implementation "com.squareup.retrofit2:converter-gson:$retrofit2_version"
        
        // Swipe-Refresh
        implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
    
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

##### I will update more. Will update Restful-API ability

---

I hope you enjoy :-)

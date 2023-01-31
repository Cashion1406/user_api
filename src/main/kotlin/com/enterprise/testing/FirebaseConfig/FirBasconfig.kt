package com.enterprise.testing.FirebaseConfig

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.IOException
import javax.annotation.PostConstruct

@Service
class FirBasconfig {

    @PostConstruct
    fun FirebaseConnection() {
        val serviceAccount = FileInputStream("./firebaseConfig.json")

        try {

            val options: FirebaseOptions = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build()

            FirebaseApp.initializeApp(options)

        } catch (e: IOException) {
            e.printStackTrace()

        }


    }


}
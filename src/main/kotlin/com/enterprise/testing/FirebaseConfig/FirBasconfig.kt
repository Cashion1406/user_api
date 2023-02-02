package com.enterprise.testing.FirebaseConfig

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import javax.annotation.PostConstruct


@Service
class FirBasconfig {
    @PostConstruct
    fun FirebaseConnection() {
        /*        val serviceAccount = FileInputStream("./firebaseConfig.json")
                val file: File = ResourceUtils.getFile("classpath:firebaseConfig.json")*/
        val resource: Resource = ClassPathResource("./firebaseConfig.json")
        val inputStream: InputStream = resource.inputStream
        try {

            val options: FirebaseOptions = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .build()
            FirebaseApp.initializeApp(options)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
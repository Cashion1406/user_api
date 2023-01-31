package com.enterprise.testing.services

import com.enterprise.testing.model.ClientDeleteResponse
import com.enterprise.testing.model.ClientListResponse
import com.enterprise.testing.model.ClientResponse
import com.enterprise.testing.model.User
import com.google.api.core.ApiFuture
import com.google.cloud.firestore.*
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserService {
    private lateinit var createUser: ApiFuture<WriteResult>
    private lateinit var getallclient: ApiFuture<QuerySnapshot>


    fun createUser(user: User): ClientResponse {


        //get firestore connected
        val fireStore: Firestore = FirestoreClient.getFirestore()


        val documentReference: DocumentReference = fireStore.collection("Users").document()

        user.id = documentReference.id
        createUser = documentReference.set(user)

        val updatetime = createUser.get().updateTime.toDate()

        return ClientResponse(user.id!!, updatetime)

    }

    fun getallUser(): List<User> {

        //If need convert to Object list, use ClientListResponse
        //get firestore connected
        val fireStore: Firestore = FirestoreClient.getFirestore()

        getallclient = fireStore.collection("Users").get()

        //List of querry of user
        val list: List<QueryDocumentSnapshot> = getallclient.get().documents


        //Kotlin
        val userList: List<User> = list.map { doc -> doc.toObject(User::class.java) }.toList()

        //Java version
        //val userList: List<User> = list.stream().map { doc -> doc.toObject(User::class.java) }.collect(Collectors.toList())

        return userList
    }

    fun getUserByName(key: String): ClientListResponse {

        val fireStore: Firestore = FirestoreClient.getFirestore()

        getallclient = fireStore.collection("Users").whereGreaterThanOrEqualTo("name", key).whereLessThan("name", key + 'z').get()

        val list: List<QueryDocumentSnapshot> = getallclient.get().documents
        val userList: List<User> = list.map { doc -> doc.toObject(User::class.java) }.toList()

        return ClientListResponse(userList)
    }

    fun updateUser(user: User): ClientResponse {
        val fireStore: Firestore = FirestoreClient.getFirestore()
        val documentReference: DocumentReference = fireStore.collection("Users").document(user.id!!)
        createUser = documentReference.set(user)
        val updatetime = createUser.get().updateTime.toDate()

        return ClientResponse(user.id!!, updatetime)

    }

    fun deletUser(id: String): ClientDeleteResponse {
        //Connect firebase
        val fireStore: Firestore = FirestoreClient.getFirestore()

        //Locate firebase colelction using user passed id in the param
        val documentReference: DocumentReference = fireStore.collection("Users").document(id)

        createUser = documentReference.delete()


        //Testing return string message
        //return "${user.name} + has been deleted successfully at ${createUser.get().updateTime.toDate()}"
        val updatetime = createUser.get().updateTime.toDate()

        return ClientDeleteResponse(updatetime, true)
    }

}
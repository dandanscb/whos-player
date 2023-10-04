package br.com.whosplayer.commons.database

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import br.com.whosplayer.app.whosplayer.repository.response.SoccerPlayerResponse
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun importDataFromJson(documentId: String, soccerPlayer: SoccerPlayerResponse) {
    val jsonString = Json.encodeToString(soccerPlayer)

    val db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection("stages")

    val data = hashMapOf(
        "stages" to jsonString
    )

    val documentRef = collectionRef.document(documentId)

    documentRef
        .set(data)
        .addOnSuccessListener {
            println("Item adicionado com ID personalizado: $documentId")
        }
        .addOnFailureListener { e ->
            println("Erro ao adicionar item: $e")
        }
}

@SuppressLint("HardwareIds")
fun getAndroidID(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}

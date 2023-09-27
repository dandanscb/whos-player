package br.com.whosplayer.app.whosplayer.repository.mock

import br.com.whosplayer.app.whosplayer.repository.response.StageResponse
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun importDataFromJson() {
    val firestore = Firebase.firestore
    val data: List<StageResponse> = WhosPlayerMock.getStageResponseMock()
    val jsonString = Json.encodeToString(data)

    firestore.collection("stages")
        .add(mapOf("stages" to jsonString))
        .addOnSuccessListener { documentReference ->
            println("Item adicionado com ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Erro ao adicionar item: $e")
        }
}
package br.com.whosplayer.app.whosplayer.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class WhosPlayerRepositoryImpl(private val db: FirebaseFirestore) : WhosPlayerRepository {

    override suspend fun getSoccerPlayer(level: Int): WhosPlayerRepositoryState {
        val collectionName = "stages"
        val documentId = "level $level"
        val documentRef = db.collection(collectionName).document(documentId)
        return try {
            val document = documentRef.get().await()
            if (document.exists()) {
                val data = document.data
                WhosPlayerRepositoryState.GetSoccerPlayer(data)
            } else {
                WhosPlayerRepositoryState.NotFound
            }
        } catch (e: Exception) {
            WhosPlayerRepositoryState.Exception
        }
    }
}

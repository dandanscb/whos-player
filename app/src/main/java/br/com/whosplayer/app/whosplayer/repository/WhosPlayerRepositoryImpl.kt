package br.com.whosplayer.app.whosplayer.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class WhosPlayerRepositoryImpl(private val db: FirebaseFirestore) : WhosPlayerRepository {

    override suspend fun getSoccerPlayer(level: Int): WhosPlayerRepositoryState.SoccerPlayerRepositoryState {
        val collectionName = "stages"
        val documentId = "level $level"
        val documentRef = db.collection(collectionName).document(documentId)
        return try {
            val document = documentRef.get().await()
            if (document.exists()) {
                val data = document.data
                WhosPlayerRepositoryState.SoccerPlayerRepositoryState.GetSoccerPlayer(data)
            } else {
                WhosPlayerRepositoryState.SoccerPlayerRepositoryState.NotFound
            }
        } catch (e: Exception) {
            WhosPlayerRepositoryState.SoccerPlayerRepositoryState.Exception
        }
    }

    override suspend fun getPlayerLevel(androidId: String): WhosPlayerRepositoryState.GetPlayerLevelRepositoryState {
        val playerRef = db.collection("players").document(androidId)
        return try {
            val player = playerRef.get().await()
            if (player.exists()) {
                val level = player.getLong("level")
                level?.let {
                    WhosPlayerRepositoryState.GetPlayerLevelRepositoryState.GetPlayerLevel(it)
                } ?: WhosPlayerRepositoryState.GetPlayerLevelRepositoryState.Error
            } else {
                WhosPlayerRepositoryState.GetPlayerLevelRepositoryState.NotFoundPlayer
            }
        } catch (e: Exception) {
            WhosPlayerRepositoryState.GetPlayerLevelRepositoryState.Error
        }
    }

    override suspend fun savePlayerLevel(
        androidId: String,
        level: Int
    ): WhosPlayerRepositoryState.SavePlayerLevelRepositoryState {
        val playerRef = db.collection("players").document(androidId)
        return try {
            playerRef.set(mapOf("level" to level))
            WhosPlayerRepositoryState.SavePlayerLevelRepositoryState.Success
        } catch (e: Exception) {
            WhosPlayerRepositoryState.SavePlayerLevelRepositoryState.Error
        }
    }
}

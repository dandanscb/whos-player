package br.com.whosplayer.app.whosplayer.repository.mapper

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel
import br.com.whosplayer.app.whosplayer.repository.model.TeamModel
import br.com.whosplayer.app.whosplayer.repository.model.TipsModel
import br.com.whosplayer.app.whosplayer.repository.response.SoccerPlayerResponse
import br.com.whosplayer.app.whosplayer.repository.response.TeamResponse

class WhosPlayerMapperImpl : WhosPlayerMapper {

    override fun convertSoccerPlayerResponseToModel(
        soccerPlayerResponse: SoccerPlayerResponse,
        numberOfColumns: Int
    ): SoccerPlayerModel {
        return SoccerPlayerModel(
            name = soccerPlayerResponse.name,
            nameLetterByLetter = getLettersFromName(soccerPlayerResponse.name),
            tips = TipsModel(
                position = soccerPlayerResponse.tips.position,
                nationality = soccerPlayerResponse.tips.nationality,
                dateOfBirth = soccerPlayerResponse.tips.dateOfBirth
            ),
            teams = convertTeamsResponseToModel(
                if (numberOfColumns == 3) {
                    soccerPlayerResponse.teams
                } else {
                    getListWithTwoTeamsResponse(
                        convertListListIntoJustAList(soccerPlayerResponse.teams)
                    )
                }
            ),
            picture = soccerPlayerResponse.picture
        )
    }

    private fun getLettersFromName(soccerPlayerFullName: String): List<List<Char>> {
        val names = soccerPlayerFullName.split(" ")
        val list = mutableListOf<List<Char>>()

        for (item in names) {
            val nameLetterByLetter = mutableListOf<Char>()
            for (letter in item) {
                nameLetterByLetter.add(letter)
            }
            list.add(nameLetterByLetter)
        }

        return list
    }

    private fun convertTeamsResponseToModel(teamsResponse: List<List<TeamResponse>>): List<List<TeamModel>> {
        val result = mutableListOf<List<TeamModel>>()

        teamsResponse.map { teamsResponseList ->
            val teamsModel = mutableListOf<TeamModel>()
            teamsResponseList.map {
                teamsModel.add(
                    TeamModel(
                        name = it.name,
                        crest = it.crest,
                        year = it.year,
                        lastTeam = it.lastTeam
                    )
                )
            }
            result.add(teamsModel)
        }

        return result
    }

    private fun convertListListIntoJustAList(response: List<List<TeamResponse>>) =
        response.flatten().toList()


    private fun getListWithTwoTeamsResponse(
        response: List<TeamResponse>
    ): List<List<TeamResponse>> {
        response.last().lastTeam = true
        val list = mutableListOf<List<TeamResponse>>()
        val subListSize = 2
        var currentIndex = 0

        while (currentIndex < response.size) {
            val endIndex =
                if (currentIndex + subListSize > response.size) {
                    response.size
                } else {
                    currentIndex + subListSize
                }
            val sublist = response.subList(currentIndex, endIndex)
            list.add(sublist)
            currentIndex += subListSize
        }

        return list
    }
}

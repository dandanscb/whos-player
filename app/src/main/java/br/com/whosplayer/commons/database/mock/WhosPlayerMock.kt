package br.com.whosplayer.commons.database.mock

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel
import br.com.whosplayer.app.whosplayer.repository.model.StageModel
import br.com.whosplayer.app.whosplayer.repository.model.TeamModel
import br.com.whosplayer.app.whosplayer.repository.model.TipsModel
import br.com.whosplayer.app.whosplayer.repository.response.SoccerPlayerResponse
import br.com.whosplayer.app.whosplayer.repository.response.StageResponse
import br.com.whosplayer.app.whosplayer.repository.response.TeamResponse
import br.com.whosplayer.app.whosplayer.repository.response.TipsResponse
import br.com.whosplayer.app.whosplayer.view.WhosPlayerActivity

object WhosPlayerMock {

    private const val FIRST_INDEX = 0

    fun getStageModelMock(): List<StageModel> = listOf(
        StageModel(
            level = 0,
            soccerPlayer = SoccerPlayerModel(
                name = "Neymar Junior",
                nameLetterByLetter = getLettersFromName("Neymar Junior"),
                tips = TipsModel(
                    position = "Atacante",
                    nationality = "Brasileiro",
                    dateOfBirth = "5 de fevereiro de 1992"
                ),
                teams = getListWithFourTeams(
                    listOf(
                        TeamModel(
                            name = "Santos",
                            crest = "https://logodetimes.com/times/santos/logo-santos-4096.png",
                            year = "2009-2013"
                        ),
                        TeamModel(
                            name = "Barcelona",
                            crest = "https://logodetimes.com/times/barcelona/logo-barcelona-4096.png",
                            year = "2013-2017"
                        ),
                        TeamModel(
                            name = "Paris Saint-Germain",
                            crest = "https://logodetimes.com/times/paris-saint-germain/paris-saint-germain-4096.png",
                            year = "2017-2023"
                        ),
                        TeamModel(
                            name = "Al-Hilal",
                            crest = "https://logodetimes.com/times/al-hilal/al-hilal-2023-4096.png",
                            year = "2023"
                        )
                    )
                ),
                picture = ""
            )
        ),
        StageModel(
            level = 1,
            soccerPlayer = SoccerPlayerModel(
                name = "Messi",
                nameLetterByLetter = getLettersFromName("Messi"),
                tips = TipsModel(
                    position = "Atacante",
                    nationality = "Argentino",
                    dateOfBirth = "24 de junho de 1987"
                ),
                teams = getListWithFourTeams(
                    listOf(
                        TeamModel(
                            name = "Newell's Old Boys",
                            crest = "https://logodetimes.com/times/newells-old-boys/logo-newells-old-boys-4096.png",
                            year = "2009-2013"
                        ),
                        TeamModel(
                            name = "Barcelona",
                            crest = "https://logodetimes.com/times/barcelona/logo-barcelona-4096.png",
                            year = "2000-2021"
                        ),
                        TeamModel(
                            name = "Paris Saint-Germain",
                            crest = "https://logodetimes.com/times/paris-saint-germain/paris-saint-germain-4096.png",
                            year = "2021-2023"
                        ),
                        TeamModel(
                            name = "Inter Miami",
                            crest = "https://content.sportslogos.net/logos/9/6621/full/7559_inter_miami_cf-primary-20201.png",
                            year = "2023"
                        )
                    )
                ),
                picture = ""
            )
        ),
        StageModel(
            level = 2,
            soccerPlayer = SoccerPlayerModel(
                name = "Cristiano Ronaldo",
                nameLetterByLetter = getLettersFromName("Cristiano Ronaldo"),
                tips = TipsModel(
                    position = "Atacante",
                    nationality = "Portugues",
                    dateOfBirth = "5 de fevereiro de 1985"
                ),
                teams = getListWithFourTeams(
                    listOf(
                        TeamModel(
                            name = "Sporting",
                            crest = "https://logodetimes.com/times/sporting-portugal/logo-sporting-portugal.png",
                            year = "2001-2003"
                        ),
                        TeamModel(
                            name = "Manchester United",
                            crest = "https://logodetimes.com/times/manchester-united-football-club/manchester-united-football-club-4096.png",
                            year = "2003-2009"
                        ),
                        TeamModel(
                            name = "Real Madrid",
                            crest = "https://logodetimes.com/times/real-madrid/logo-real-madrid-4096.png",
                            year = "2009-2018"
                        ),
                        TeamModel(
                            name = "Juventus",
                            crest = "https://logodetimes.com/times/juventus-football-club/juventus-football-club-8192.png",
                            year = "2018-2021"
                        ),
                        TeamModel(
                            name = "Manchester United",
                            crest = "https://logodetimes.com/times/manchester-united-football-club/manchester-united-football-club-4096.png",
                            year = "2021-2022"
                        ),
                        TeamModel(
                            name = "Al-Nassr",
                            crest = "https://logodownload.org/wp-content/uploads/2023/07/al-nassr-fc-logo-0.png",
                            year = "2023"
                        )
                    )
                ),
                picture = ""
            )
        )
    )

    fun getStageResponseMock(): List<StageResponse> = listOf(
        StageResponse(
            level = 0,
            soccerPlayer = SoccerPlayerResponse(
                name = "Neymar",
                tips = TipsResponse(
                    position = "Atacante",
                    nationality = "Brasileiro",
                    dateOfBirth = "5 de fevereiro de 1992"
                ),
                teams = getListWithFourTeamsResponse(
                    listOf(
                        TeamResponse(
                            name = "Santos",
                            crest = "https://logodetimes.com/times/santos/logo-santos-4096.png",
                            year = "2009-2013"
                        ),
                        TeamResponse(
                            name = "Barcelona",
                            crest = "https://logodetimes.com/times/barcelona/logo-barcelona-4096.png",
                            year = "2013-2017"
                        ),
                        TeamResponse(
                            name = "Paris Saint-Germain",
                            crest = "https://logodetimes.com/times/paris-saint-germain/paris-saint-germain-4096.png",
                            year = "2017-2023"
                        ),
                        TeamResponse(
                            name = "Al-Hilal",
                            crest = "https://logodetimes.com/times/al-hilal/al-hilal-2023-4096.png",
                            year = "2023"
                        )
                    )
                ),
                picture = ""
            )
        ),
        StageResponse(
            level = 1,
            soccerPlayer = SoccerPlayerResponse(
                name = "Messi",
                tips = TipsResponse(
                    position = "Atacante",
                    nationality = "Argentino",
                    dateOfBirth = "24 de junho de 1987"
                ),
                teams = getListWithFourTeamsResponse(
                    listOf(
                        TeamResponse(
                            name = "Newell's Old Boys",
                            crest = "https://logodetimes.com/times/newells-old-boys/logo-newells-old-boys-4096.png",
                            year = "2009-2013"
                        ),
                        TeamResponse(
                            name = "Barcelona",
                            crest = "https://logodetimes.com/times/barcelona/logo-barcelona-4096.png",
                            year = "2000-2021"
                        ),
                        TeamResponse(
                            name = "Paris Saint-Germain",
                            crest = "https://logodetimes.com/times/paris-saint-germain/paris-saint-germain-4096.png",
                            year = "2021-2023"
                        ),
                        TeamResponse(
                            name = "Inter Miami",
                            crest = "https://content.sportslogos.net/logos/9/6621/full/7559_inter_miami_cf-primary-20201.png",
                            year = "2023"
                        )
                    )
                ),
                picture = ""
            )
        ),
        StageResponse(
            level = 2,
            soccerPlayer = SoccerPlayerResponse(
                name = "Cristiano Ronaldo",
                tips = TipsResponse(
                    position = "Atacante",
                    nationality = "Portugues",
                    dateOfBirth = "5 de fevereiro de 1985"
                ),
                teams = getListWithFourTeamsResponse(
                    listOf(
                        TeamResponse(
                            name = "Sporting",
                            crest = "https://logodetimes.com/times/sporting-portugal/logo-sporting-portugal.png",
                            year = "2001-2003"
                        ),
                        TeamResponse(
                            name = "Manchester United",
                            crest = "https://logodetimes.com/times/manchester-united-football-club/manchester-united-football-club-4096.png",
                            year = "2003-2009"
                        ),
                        TeamResponse(
                            name = "Real Madrid",
                            crest = "https://logodetimes.com/times/real-madrid/logo-real-madrid-4096.png",
                            year = "2009-2018"
                        ),
                        TeamResponse(
                            name = "Juventus",
                            crest = "https://logodetimes.com/times/juventus-football-club/juventus-football-club-8192.png",
                            year = "2018-2021"
                        ),
                        TeamResponse(
                            name = "Manchester United",
                            crest = "https://logodetimes.com/times/manchester-united-football-club/manchester-united-football-club-4096.png",
                            year = "2021-2022"
                        ),
                        TeamResponse(
                            name = "Al-Nassr",
                            crest = "https://logodownload.org/wp-content/uploads/2023/07/al-nassr-fc-logo-0.png",
                            year = "2023"
                        )
                    )
                ),
                picture = ""
            )
        )
    )

    private fun getListWithFourTeamsResponse(response: List<TeamResponse>): List<List<TeamResponse>> {
        response.last().lastTeam = true
        val list = mutableListOf<List<TeamResponse>>()
        val subListSize = WhosPlayerActivity.spanCount
        var currentIndex = FIRST_INDEX

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

    private fun getListWithFourTeams(model: List<TeamModel>): List<List<TeamModel>> {
        model.last().lastTeam = true
        val list = mutableListOf<List<TeamModel>>()
        val subListSize = WhosPlayerActivity.spanCount
        var currentIndex = FIRST_INDEX

        while (currentIndex < model.size) {
            val endIndex =
                if (currentIndex + subListSize > model.size) {
                    model.size
                } else {
                    currentIndex + subListSize
                }
            val sublist = model.subList(currentIndex, endIndex)
            list.add(sublist)
            currentIndex += subListSize
        }

        return list
    }

    private fun getLettersFromName(soccerPlayerFullName: String) : List<List<Char>> {
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
}

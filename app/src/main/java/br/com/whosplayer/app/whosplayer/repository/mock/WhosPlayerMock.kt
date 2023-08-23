package br.com.whosplayer.app.whosplayer.repository.mock

import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel
import br.com.whosplayer.app.whosplayer.repository.model.StageModel
import br.com.whosplayer.app.whosplayer.repository.model.TeamModel
import br.com.whosplayer.app.whosplayer.repository.model.TipsModel

object WhosPlayerMock {

    fun getStageModelMock(): List<StageModel> = listOf(
        StageModel(
            level = 0,
            soccerPlayer = SoccerPlayerModel(
                name = "Neymar",
                tips = TipsModel(
                    position = "Atacante",
                    nationality = "Brasileiro",
                    dateOfBirth = "5 de fevereiro de 1992"
                ),
                teams = listOf(
                    TeamModel(
                        name = "Santos",
                        crest = "https://pt.wikipedia.org/wiki/Santos_Futebol_Clube#/media/Ficheiro:Santos_Logo.png",
                        year = "2009-2013"
                    ),
                    TeamModel(
                        name = "Barcelona",
                        crest = "https://pt.wikipedia.org/wiki/Futbol_Club_Barcelona#/media/Ficheiro:FCBarcelona.svg",
                        year = "2013-2017"
                    ),
                    TeamModel(
                        name = "Paris Saint-Germain",
                        crest = "https://pt.wikipedia.org/wiki/Paris_Saint-Germain_Football_Club#/media/Ficheiro:Logo_PSG.png",
                        year = "2017-2023"
                    ),
                    TeamModel(
                        name = "Al-Hilal",
                        crest = "https://pt.wikipedia.org/wiki/Al-Hilal_Saudi_Football_Club#/media/Ficheiro:Alhilal_FC.png",
                        year = "2023"
                    )
                ),
                picture = "https://pt.wikipedia.org/wiki/Neymar#/media/Ficheiro:Bra-Cos_(1)_(cropped).jpg"
            )
        ),
        StageModel(
            level = 1,
            soccerPlayer = SoccerPlayerModel(
                name = "Messi",
                tips = TipsModel(
                    position = "Atacante",
                    nationality = "Argentino",
                    dateOfBirth = "24 de junho de 1987"
                ),
                teams = listOf(
                    TeamModel(
                        name = "Newell's Old Boys",
                        crest = "https://pt.wikipedia.org/wiki/Club_Atl%C3%A9tico_Newell%27s_Old_Boys#/media/Ficheiro:Escudo_del_Club_Atl%C3%A9tico_Newell's_Old_Boys_de_Rosario.svg",
                        year = "2009-2013"
                    ),
                    TeamModel(
                        name = "Barcelona",
                        crest = "https://pt.wikipedia.org/wiki/Futbol_Club_Barcelona#/media/Ficheiro:FCBarcelona.svg",
                        year = "2000-2021"
                    ),
                    TeamModel(
                        name = "Paris Saint-Germain",
                        crest = "https://pt.wikipedia.org/wiki/Paris_Saint-Germain_Football_Club#/media/Ficheiro:Logo_PSG.png",
                        year = "2021-2023"
                    ),
                    TeamModel(
                        name = "Inter Miami",
                        crest = "https://pt.wikipedia.org/wiki/Al-Hilal_Saudi_Football_Club#/media/Ficheiro:Alhilal_FC.png",
                        year = "2023"
                    )
                ),
                picture = "https://pt.wikipedia.org/wiki/Lionel_Messi#/media/Ficheiro:Lionel-Messi-Argentina-2022-FIFA-World-Cup_(cropped).jpg"
            )
        ),
        StageModel(
            level = 2,
            soccerPlayer = SoccerPlayerModel(
                name = "Cristiano Ronaldo",
                tips = TipsModel(
                    position = "Atacante",
                    nationality = "Portugues",
                    dateOfBirth = "5 de fevereiro de 1985"
                ),
                teams = listOf(
                    TeamModel(
                        name = "Sporting",
                        crest = "https://pt.wikipedia.org/wiki/Sporting_Clube_de_Portugal#/media/Ficheiro:Sporting_Clube_de_Portugal.png",
                        year = "2001-2003"
                    ),
                    TeamModel(
                        name = "Manchester United",
                        crest = "https://pt.wikipedia.org/wiki/Manchester_United_F.C.#/media/Ficheiro:Manchester_United_FC_logo.png",
                        year = "2003-2009"
                    ),
                    TeamModel(
                        name = "Real Madrid",
                        crest = "https://pt.wikipedia.org/wiki/Real_Madrid_Club_de_F%C3%BAtbol#/media/Ficheiro:Real_Madrid.png",
                        year = "2009-2018"
                    ),
                    TeamModel(
                        name = "Juventus",
                        crest = "https://pt.wikipedia.org/wiki/Juventus_Football_Club#/media/Ficheiro:Juventus_FC_2017_icon_(black).svg",
                        year = "2018-2021"
                    ),
                    TeamModel(
                        name = "Manchester United",
                        crest = "https://pt.wikipedia.org/wiki/Manchester_United_F.C.#/media/Ficheiro:Manchester_United_FC_logo.png",
                        year = "2021-2022"
                    ),
                    TeamModel(
                        name = "Al-Nassr",
                        crest = "https://pt.wikipedia.org/wiki/Al-Nassr_Football_Club#/media/Ficheiro:Al-Nassr_Football_Club.png",
                        year = "2023"
                    )
                ),
                picture = "https://pt.wikipedia.org/wiki/Cristiano_Ronaldo#/media/Ficheiro:Cristiano_Ronaldo_WC2022_-_01.jpg"
            )
        )
    )
}

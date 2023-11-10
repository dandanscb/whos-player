package br.com.whosplayer.app.whosplayer.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.whosplayer.R
import br.com.whosplayer.app.help.view.HelpActivity
import br.com.whosplayer.app.whosplayer.view.adapter.NameLetterByLetterAdapter
import br.com.whosplayer.app.whosplayer.view.adapter.TeamCrestAdapter
import br.com.whosplayer.app.whosplayer.view.utils.NonScrollableGridLayoutManager
import br.com.whosplayer.databinding.ActivityWhosPlayerBinding
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModelProvider
import br.com.whosplayer.app.report.view.ReportActivity
import br.com.whosplayer.app.whosplayer.repository.model.SoccerPlayerModel
import br.com.whosplayer.app.whosplayer.repository.model.TeamModel
import br.com.whosplayer.app.whosplayer.repository.model.TipsModel
import br.com.whosplayer.app.whosplayer.viewmodel.WhosPlayerViewModel
import br.com.whosplayer.app.whosplayer.viewmodel.WhosPlayerViewModelFactory
import br.com.whosplayer.app.whosplayer.viewmodel.WhosPlayerViewState
import br.com.whosplayer.commons.database.getAndroidID
import br.com.whosplayer.commons.view.CustomSplashScreen
import br.com.whosplayer.commons.view.CustomTipsTextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.text.Normalizer
import java.util.concurrent.TimeUnit
import kotlin.math.sin

class WhosPlayerActivity : AppCompatActivity(), NameLetterByLetterAdapter.EditTextFocusListener {

    private lateinit var binding: ActivityWhosPlayerBinding

    private var teamCrestAdapter = mutableListOf<TeamCrestAdapter>()

    private var nameLetterByLetterAdapter = mutableListOf<NameLetterByLetterAdapter>()
    private var recyclerViewReference = mutableListOf<RecyclerView>()
    private var initialX: Float = 0f

    private var viewModel: WhosPlayerViewModel? = null
    private lateinit var soccerPlayerName: String
    private var currentLevel: Int? = null

    private var dateInterstitialAd: InterstitialAd? = null
    private var byLevelInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.extras?.let {
            currentLevel = it.getInt(LEVEL_STATE)
        }
        MobileAds.initialize(this) {
            // not used
        }

        init()
        initViewModel()
        initObservable()
    }

    private fun init() {
        binding = ActivityWhosPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialX = binding.container.x

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        configDateButtons()
        configConfirmationButton()

        startAds()

//        val pair = WhosPlayerMock.getSoccerPlayerResponse()
//        if (pair.first.isNotEmpty()) {
//            importDataFromJson(pair.first, pair.second)
//        }
    }

    private fun startAds() {
        val adView = AdView(this)

        adView.setAdSize(AdSize.BANNER)

//        adView.adUnitId = this.getString(R.string.whos_player_banner_ad)
        adView.adUnitId = this.getString(R.string.whos_player_banner_ad_test)

        loadInterstitialAds()
    }

    private fun initViewModel() {
        val factory = WhosPlayerViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[WhosPlayerViewModel::class.java]

        viewModel?.getSoccerPlayer(currentLevel)
    }

    private fun initObservable() {
        viewModel?.viewState?.observe(this) {
            when (it) {
                is WhosPlayerViewState.WhosPlayerSoccerPlayerViewState.GetSoccerPlayer -> {
                    showSoccerPlayerInformation(it.level, it.soccerPlayer)
                }

                is WhosPlayerViewState.WhosPlayerSoccerPlayerViewState.ShowLoading -> {
                    showLoading()
                }

                is WhosPlayerViewState.WhosPlayerSoccerPlayerViewState.HideLoading -> {
                    hideLoading()
                }

                is WhosPlayerViewState.WhosPlayerSoccerPlayerViewState.GenericError -> {
                    binding.errorScreen.visibility = View.VISIBLE
                    binding.errorScreen.closeClickListener {
                        finish()
                    }
                    binding.frameLayout.visibility = View.GONE
                }
            }
        }
        viewModel?.saveLevelViewState?.observe(this) {
            when (it) {
                is WhosPlayerViewState.WhosPlayerSaveLevelViewState.Success -> {
                    teamCrestAdapter.clear()
                    nameLetterByLetterAdapter.clear()
                    recyclerViewReference.clear()

                    currentLevel?.let { level ->
                        currentLevel = level + 1
                    }

                    binding.cardViewContainer.removeAllViews()
                    binding.fieldLettersContainer.removeAllViews()
                    viewModel?.getSoccerPlayer(currentLevel)
                }

                is WhosPlayerViewState.WhosPlayerSaveLevelViewState.ShowLoading -> {
                    // not used yet
                }

                is WhosPlayerViewState.WhosPlayerSaveLevelViewState.HideLoading -> {
                    // not used yet
                }

                is WhosPlayerViewState.WhosPlayerSaveLevelViewState.GenericError -> {
                    binding.errorScreen.visibility = View.VISIBLE
                    binding.errorScreen.closeClickListener {
                        finish()
                    }
                    binding.frameLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun showSoccerPlayerInformation(level: Int, soccerPlayer: SoccerPlayerModel) {
        currentLevel = level


        currentLevel?.let {
            if (it % 10 == 0) {
                if (byLevelInterstitialAd != null) {
                    byLevelInterstitialAd?.show(this)
                } else {
                    Log.d(TAG, "The interstitial ad wasn't ready yet.")
                }
            }

            binding.textLevel.typeface = Typeface.create("sans-serif", Typeface.NORMAL)
            binding.textLevel.text = this.getString(R.string.whos_player_text_level, it)
        }

        soccerPlayerName = soccerPlayer.name
        soccerPlayer.name
        displayCrests(soccerPlayer.teams)
        showFieldForLetters(soccerPlayer.nameLetterByLetter)
        configTipsButtons(soccerPlayer.tips)

        binding.dateButton.visibility = View.VISIBLE
    }

    private fun displayCrests(teams: List<List<TeamModel>>) {
        teams.forEach {
            val recyclerView = RecyclerView(this)

            val layoutManager = NonScrollableGridLayoutManager(this, SPAN_COUNT)
            val screenWidth = resources.displayMetrics.widthPixels
            val desiredColumnWidth = resources.getDimensionPixelSize(R.dimen.whos_player_80dp)
            val columns = screenWidth / desiredColumnWidth
            layoutManager.spanCount = columns.coerceAtMost(SPAN_COUNT)

            recyclerView.layoutManager = layoutManager

            val adapter = TeamCrestAdapter(this, it)
            recyclerView.adapter = adapter

            binding.cardViewContainer.addView(recyclerView)
            recyclerView.layoutParams.width = RecyclerView.LayoutParams.WRAP_CONTENT
            teamCrestAdapter.add(adapter)
        }
    }

    private fun showFieldForLetters(nameLetterByLetter: List<List<Char>>) {
        var index = FIRST_INDEX
        nameLetterByLetter.forEach {
            val recyclerView = RecyclerView(this)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER

            val layoutManager = NonScrollableGridLayoutManager(this, it.size)
            recyclerView.layoutManager = layoutManager

            val adapter = NameLetterByLetterAdapter(index, it)
            adapter.editTextFocusListener = this
            recyclerView.adapter = adapter

            recyclerView.layoutParams = layoutParams

            binding.fieldLettersContainer.addView(recyclerView)
            recyclerView.layoutParams.width = RecyclerView.LayoutParams.WRAP_CONTENT
            recyclerViewReference.add(recyclerView)
            nameLetterByLetterAdapter.add(adapter)
            index++
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_whos_player, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_help -> {
                val intent = HelpActivity.newInstance(this)
                startActivity(intent)
                true
            }

            R.id.action_report -> {
                val intent = ReportActivity.newInstance(this)
                startActivity(intent)
                true
            }


            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onLetterTyped(recyclerViewPosition: Int, position: Int, isDelete: Boolean) {
        if (position < nameLetterByLetterAdapter[recyclerViewPosition].itemCount) {
            if (position == NEGATIVE_NUMBER_ONE) {
                if (recyclerViewPosition - NUMBER_ONE >= FIRST_INDEX) {
                    val layoutManager =
                        recyclerViewReference[recyclerViewPosition - NUMBER_ONE].layoutManager as LinearLayoutManager
                    layoutManager.findViewByPosition(
                        recyclerViewReference[recyclerViewPosition - NUMBER_ONE].size - NUMBER_ONE
                    )
                        ?.requestFocus()

                    if (isDelete) {
                        layoutManager.findViewByPosition(
                            recyclerViewReference[recyclerViewPosition - NUMBER_ONE].size - NUMBER_ONE
                        )
                            ?.findViewById<AppCompatEditText>(R.id.letterEditText)?.setText("")
                    }
                }
            } else {
                val layoutManager =
                    recyclerViewReference[recyclerViewPosition].layoutManager as LinearLayoutManager
                layoutManager.findViewByPosition(position)
                    ?.findViewById<AppCompatEditText>(R.id.letterEditText)?.requestFocus()

                if (isDelete) {
                    layoutManager.findViewByPosition(position)
                        ?.findViewById<AppCompatEditText>(R.id.letterEditText)?.setText("")
                }
            }
        } else {
            if (recyclerViewPosition + NUMBER_ONE < recyclerViewReference.size) {
                val layoutManager =
                    recyclerViewReference[recyclerViewPosition + NUMBER_ONE].layoutManager as LinearLayoutManager
                layoutManager.findViewByPosition(FIRST_INDEX)
                    ?.findViewById<AppCompatEditText>(R.id.letterEditText)
                    ?.requestFocus()

                if (isDelete) {
                    layoutManager.findViewByPosition(FIRST_INDEX)
                        ?.findViewById<AppCompatEditText>(R.id.letterEditText)
                        ?.setText("")
                }
            }
        }
    }

    override fun checkIfAllLettersAreFilledIn() {
        binding.confirmationButton.isEnabled = returnIfAllLettersAreFilledIn()
    }

    private fun returnIfAllLettersAreFilledIn(): Boolean {
        nameLetterByLetterAdapter.map {
            if (!it.getIfLettersAreFilledIn()) {
                return false
            }
        }
        return true
    }

    private fun configTipsButtons(tips: TipsModel) {
        val tipsMessage = getTipsMessages(tips)
        var remainingTips = tipsMessage.size

        binding.remainingHintNumbers.text = remainingTips.toString()

        binding.tipsButton.setOnClickListener {
            if (remainingTips != FIRST_INDEX) {
                showCustomDialog(remainingTips, tipsMessage)
                remainingTips--
                configAnimationNumberTips()
                binding.remainingHintNumbers.text = remainingTips.toString()
            } else {
                showCustomDialog(NUMBER_ONE, tipsMessage)
                binding.remainingHintNumbers.text = remainingTips.toString()
            }
        }

    }

    private fun getTipsMessages(tips: TipsModel): List<String> {
        val messages = mutableListOf<String>()

        if (tips.dateOfBirth.isNotEmpty()) {
            messages.add("A data de nascimento do jogador é ${tips.dateOfBirth}.")
        }

        if (tips.position.isNotEmpty()) {
            messages.add("A posição do jogador é ${tips.position}.")
        }

        if (tips.nationality.isNotEmpty()) {
            messages.add("A nacionalidade do jogador é ${tips.nationality}.")
        }

        return messages
    }

    private fun showLoading() {
        val splashScreen = CustomSplashScreen(this)
        val layoutParams = ViewGroup.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        binding.frameLayout.addView(splashScreen, layoutParams)
        binding.toolbar.visibility = View.GONE
        binding.nestedScroll.visibility = View.GONE
        binding.frameLayout.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.toolbar.visibility = View.VISIBLE
        binding.nestedScroll.visibility = View.VISIBLE
        binding.frameLayout.visibility = View.GONE
    }

    private fun configAnimationNumberTips() {
        val scaleXAnimator = ObjectAnimator.ofFloat(
            binding.tipsNumberAnimation,
            "scaleX",
            1.0f,
            2.0f
        )
        val scaleYAnimator =
            ObjectAnimator.ofFloat(
                binding.tipsNumberAnimation,
                "scaleY",
                1.0f,
                2.0f
            )

        val alphaAnimator = ObjectAnimator.ofFloat(
            binding.tipsNumberAnimation,
            "alpha",
            1.0f,
            0.0f
        )

        scaleXAnimator.duration = 1500
        scaleYAnimator.duration = 1500
        alphaAnimator.duration = 1500

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator)

        binding.tipsNumberAnimation.visibility = View.VISIBLE

        animatorSet.start()
    }

    private fun configDateButtons() {
        binding.dateButton.setOnClickListener {
            binding.dateButton.visibility = View.GONE

            if (dateInterstitialAd != null) {
                dateInterstitialAd?.show(this)
            } else {
                Log.d(TAG, "The interstitial ad wasn't ready yet.")
            }

            teamCrestAdapter.forEach {
                it.changeYearsPlayedVisibility()
            }
        }
    }

    private fun loadInterstitialAds() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
//            getString(R.string.whos_player_level_ad),
            getString(R.string.whos_player_interstitial_ad_test),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString())
                    byLevelInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    byLevelInterstitialAd = interstitialAd
                }
            })

        InterstitialAd.load(
            this,
//            getString(R.string.whos_player_calendar_ad),
            getString(R.string.whos_player_interstitial_ad_test),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString())
                    dateInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    dateInterstitialAd = interstitialAd
                }
            })
    }

    private fun configConfirmationButton() {
        binding.confirmationButton.setOnClickListener {
            var result = ""
            nameLetterByLetterAdapter.map {
                result += it.getItem()
            }
            val answer = removeAccentuation(soccerPlayerName.uppercase().trim().replace(" ", ""))

            if (result.uppercase() == answer) {
                val party = Party(
                    speed = 0f,
                    maxSpeed = 30f,
                    damping = 0.9f,
                    spread = 360,
                    colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                    emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
                    position = Position.Relative(0.5, 0.3)
                )
                binding.konfettiView.start(party)

                currentLevel?.let {
                    val nextLevel = it + 1

                    viewModel?.saveLevel(getAndroidID(this), nextLevel)
                }
            } else {
                val duration = 1000L
                val magnitude = 20f

                val animator = ValueAnimator.ofFloat(0f, 1f)
                animator.duration = duration
                animator.addUpdateListener { animation ->
                    val fraction = animation.animatedValue as Float
                    val offset = magnitude * sin(fraction * 20)
                    binding.container.x = initialX + offset
                }

                animator.start()

                Handler().postDelayed({ binding.container.x = initialX }, 1050L)

                nameLetterByLetterAdapter.map {
                    it.clearLetters()
                }
                val layoutManager =
                    recyclerViewReference[FIRST_INDEX].layoutManager as LinearLayoutManager
                layoutManager.findViewByPosition(FIRST_INDEX)
                    ?.findViewById<AppCompatEditText>(R.id.letterEditText)
                    ?.requestFocus()

            }
        }
    }

    private fun removeAccentuation(text: String): String {
        val textWithoutAccentuation = Normalizer.normalize(text, Normalizer.Form.NFD)
        return textWithoutAccentuation.replace("[^\\p{ASCII}]".toRegex(), "")
    }

    private fun showCustomDialog(tipsNumber: Int, tipsMessage: List<String>) {
        val dialog = Dialog(this, R.style.CustomDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alert_dialog_whos_player_tips)
        dialog.setCancelable(true)

        val closeButton = dialog.findViewById<AppCompatImageButton>(R.id.dialog_close_button)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        val tipsContainer = dialog.findViewById<LinearLayout>(R.id.tipsContainer)

        val mAdView = dialog.findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = layoutParams

        val messages = mutableListOf<Pair<String, String>>()
        for (position in tipsMessage.size downTo tipsNumber) {
            messages.add(
                Pair(
                    this.getString(
                        R.string.whos_player_custom_tips_text_title,
                        (messages.size + NUMBER_ONE).toString()
                    ),
                    tipsMessage[messages.size]
                )
            )
        }

        for (tip in messages) {
            val firstTip = CustomTipsTextView(this)
            firstTip.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            (firstTip.layoutParams as LinearLayout.LayoutParams).setMargins(
                resources.getDimension(R.dimen.whos_player_0dp).toInt(),
                resources.getDimension(R.dimen.whos_player_12dp).toInt(),
                resources.getDimension(R.dimen.whos_player_0dp).toInt(),
                resources.getDimension(R.dimen.whos_player_0dp).toInt()
            )
            firstTip.setTexts(tip.first, tip.second)
            tipsContainer.addView(firstTip)
        }

        dialog.show()
    }

    companion object {
        private const val SPAN_COUNT = 3
        private const val FIRST_INDEX = 0
        private const val NUMBER_ONE = 1
        private const val NEGATIVE_NUMBER_ONE = -1

        const val LEVEL_STATE = "LEVEL_STATE"

        private const val TAG = "INTERSTICIAL_AD"

        @JvmStatic
        fun newInstance(context: Context, level: Int): Intent =
            Intent(context, WhosPlayerActivity::class.java).apply {
                putExtra(LEVEL_STATE, level)
            }
    }
}

package cheysoff.film_o_search.ui

import android.util.Log
import android.widget.ImageButton
import cheysoff.film_o_search.MainActivity.Companion.ScreenTypes
import cheysoff.film_o_search.R
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.LikedFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment

class ChooseBar(
    private val homeButton: ImageButton,
    private val likedButton: ImageButton,
    private val ticketButton: ImageButton,
    private val searchButton: ImageButton,

    private val supportFragmentManager: androidx.fragment.app.FragmentManager

) {

    init {
        Log.d("start", "")
        homeButton.setOnClickListener {
            changeScreen(ScreenTypes.Home)
            Log.d("1", "1")

        }
        likedButton.setOnClickListener {
            changeScreen(ScreenTypes.Liked)
            Log.d("2", "2")
        }
        ticketButton.setOnClickListener {
            changeScreen(ScreenTypes.Ticket)
            Log.d("3", "3")
        }
        searchButton.setOnClickListener {
            changeScreen(ScreenTypes.Search)
            Log.d("4", "4")
        }
    }


    fun changeScreen(type: ScreenTypes) {
        var homeImage = R.drawable.home_button
        var likedImage = R.drawable.liked
        var ticketImage = R.drawable.ticket
        var profileImage = R.drawable.search

        val fragment: androidx.fragment.app.Fragment
//        TODO: CHANGE PROFILE TO SEARCH
        when (type) {
            ScreenTypes.Home -> {
                homeImage = R.drawable.home_button_selected
                fragment = HomeFragment()
            }

            ScreenTypes.Liked -> {
                likedImage = R.drawable.liked_selected
                fragment = LikedFragment()
            }

            ScreenTypes.Ticket -> {
                ticketImage = R.drawable.ticket_selected
                fragment = TicketFragment()
            }

            ScreenTypes.Search -> {
                profileImage = R.drawable.search_selected
                fragment = FragmentController.getFragment(ScreenTypes.Search)
            }
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

        homeButton.setImageResource(homeImage)
        likedButton.setImageResource(likedImage)
        ticketButton.setImageResource(ticketImage)
        searchButton.setImageResource(profileImage)
    }
}
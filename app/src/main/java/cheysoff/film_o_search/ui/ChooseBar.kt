package cheysoff.film_o_search.ui

import android.util.Log
import android.widget.ImageButton
import cheysoff.film_o_search.MainActivity.Companion.ScreenTypes
import cheysoff.film_o_search.R
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.LikedFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment

class ChooseBar(
    val homeButton: ImageButton,
    val likedButton: ImageButton,
    val ticketButton: ImageButton,
    val profileButton: ImageButton,

    val supportFragmentManager: androidx.fragment.app.FragmentManager,
    val homeFragment: HomeFragment,
    val ticketFragment: TicketFragment,
    val likedFragment: LikedFragment
) {

    init {
        Log.d("start", "")
        homeButton.setOnClickListener {
            changeScreen(ScreenTypes.Home)
            Log.d("1", "1")
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, homeFragment)
                commit()
            }
        }
        likedButton.setOnClickListener {
            changeScreen(ScreenTypes.Liked)
            Log.d("2", "2")
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, likedFragment)
                commit()
            }
        }
        ticketButton.setOnClickListener {
            changeScreen(ScreenTypes.Ticket)
            Log.d("3", "3")
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, ticketFragment)
                commit()
            }
        }
        profileButton.setOnClickListener {
            changeScreen(ScreenTypes.Profile)
            Log.d("4", "4")
        }
    }


    fun changeScreen(type: ScreenTypes) {
        var homeImage = R.drawable.home_button
        var likedImage = R.drawable.liked
        var ticketImage = R.drawable.ticket
        var profileImage = R.drawable.profile

        when (type) {
            ScreenTypes.Home -> homeImage = R.drawable.home_button_selected
            ScreenTypes.Liked -> likedImage = R.drawable.liked_selected
            ScreenTypes.Ticket -> ticketImage = R.drawable.ticket_selected
            ScreenTypes.Profile -> profileImage = R.drawable.profile_selected
        }

        homeButton.setImageResource(homeImage)
        likedButton.setImageResource(likedImage)
        ticketButton.setImageResource(ticketImage)
        profileButton.setImageResource(profileImage)
    }
}
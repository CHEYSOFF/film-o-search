package cheysoff.film_o_search.ui

import android.util.Log
import android.widget.ImageButton
import cheysoff.film_o_search.MainActivity.Companion.screenTypes
import cheysoff.film_o_search.R
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment

class ChooseBar(
    val homeButton: ImageButton,
    val likedButton: ImageButton,
    val ticketButton: ImageButton,
    val profileButton: ImageButton,

    val supportFragmentManager: androidx.fragment.app.FragmentManager,
    val homeFragment: HomeFragment,
    val ticketFragment: TicketFragment
) {

    init {
        Log.d("start", "")
        homeButton.setOnClickListener {
            changeScreen(screenTypes.home)
            Log.d("1", "1")
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, homeFragment)
                commit()
            }
        }
        likedButton.setOnClickListener {
            changeScreen(screenTypes.liked)
            Log.d("2", "2")
        }
        ticketButton.setOnClickListener {
            changeScreen(screenTypes.ticket)
            Log.d("3", "3")
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, ticketFragment)
                commit()
            }
        }
        profileButton.setOnClickListener {
            changeScreen(screenTypes.profile)
            Log.d("4", "4")
        }
    }


    fun changeScreen(type: screenTypes) {
        var homeImage = R.drawable.home_button
        var likedImage = R.drawable.liked
        var ticketImage = R.drawable.ticket
        var profileImage = R.drawable.profile

        when (type) {
            screenTypes.home -> homeImage = R.drawable.home_button_selected
            screenTypes.liked -> likedImage = R.drawable.liked_selected
            screenTypes.ticket -> ticketImage = R.drawable.ticket_selected
            screenTypes.profile -> profileImage = R.drawable.profile_selected
        }

        homeButton.setImageResource(homeImage)
        likedButton.setImageResource(likedImage)
        ticketButton.setImageResource(ticketImage)
        profileButton.setImageResource(profileImage)
    }
}
package cheysoff.film_o_search.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import cheysoff.film_o_search.R

class ChooseBar(
    val homeButton: ImageButton,
    val likedButton: ImageButton,
    val ticketButton: ImageButton
) {

    enum class screenTypes {
        home,
        liked,
        ticket
    }

    init {
        Log.d("start", "")
        homeButton.setOnClickListener {
            changeScreen(screenTypes.home)
            Log.d("1", "1")
        }
        likedButton.setOnClickListener {
            changeScreen(screenTypes.liked)
            Log.d("2", "2")
        }
        ticketButton.setOnClickListener {
            changeScreen(screenTypes.ticket)
            Log.d("3", "3")
        }
    }


    fun changeScreen(type: screenTypes) {
        var homeImage = R.drawable.home_button
        var likedImage = R.drawable.liked
        var ticketImage = R.drawable.ticket
        when (type) {
            screenTypes.home -> homeImage = R.drawable.home_button_selected
            screenTypes.liked -> likedImage = R.drawable.liked_selected
            screenTypes.ticket -> ticketImage = R.drawable.ticket_selected
        }
        homeButton.setImageResource(homeImage)
        likedButton.setImageResource(likedImage)
        ticketButton.setImageResource(ticketImage)
    }
}
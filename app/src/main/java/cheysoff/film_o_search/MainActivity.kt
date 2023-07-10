package cheysoff.film_o_search

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.data.models.MovieModel
import cheysoff.film_o_search.ui.ChooseBar
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment
import androidx.activity.viewModels
import cheysoff.film_o_search.data.database.DatabaseApplication
import cheysoff.film_o_search.data.database.MovieItemModelFactory
import cheysoff.film_o_search.ui.fragments.LikedFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var searchBar: SearchView

    private lateinit var homeButton: ImageButton
    private lateinit var likedButton: ImageButton
    private lateinit var ticketButton: ImageButton
    private lateinit var profileButton: ImageButton

    private lateinit var homeFragment: HomeFragment
    private lateinit var ticketFragment: TicketFragment
    private lateinit var likedFragment: LikedFragment

    private val viewModel: MovieViewModel by viewModels {
        MovieItemModelFactory((application as DatabaseApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        homeFragment = HomeFragment(this)
        ticketFragment = TicketFragment(this)
        likedFragment = LikedFragment(this, viewModel)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, homeFragment)
            commit()
        }

        homeButton = findViewById(R.id.homeButton)
        likedButton = findViewById(R.id.likedButton)
        ticketButton = findViewById(R.id.ticketButton)
        profileButton = findViewById(R.id.profileButton)
        val bar = ChooseBar(
            homeButton,
            likedButton,
            ticketButton,
            profileButton,
            supportFragmentManager,
            homeFragment,
            ticketFragment,
            likedFragment
        )

        val scope = CoroutineScope(EmptyCoroutineContext)
        scope.launch {
            viewModel.addMovie(MovieModel(0, "f", "f", "1000", listOf(), listOf(), "0", "0", true))
        }



        searchBar = findViewById(R.id.search_bar_id)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    companion object {
        enum class ScreenTypes {
            Home,
            Liked,
            Ticket,
            Profile
        }
    }

}
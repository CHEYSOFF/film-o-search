package cheysoff.film_o_search

import android.os.Bundle
import android.widget.ImageButton
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cheysoff.film_o_search.data.database.DatabaseApplication
import cheysoff.film_o_search.data.database.MovieItemModelFactory
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.ui.ChooseBar
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.LikedFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment

class MainActivity : AppCompatActivity() {

    private lateinit var searchBar: SearchView

    private lateinit var homeButton: ImageButton
    private lateinit var likedButton: ImageButton
    private lateinit var ticketButton: ImageButton
    private lateinit var profileButton: ImageButton

    private lateinit var homeFragment: HomeFragment
    private lateinit var ticketFragment: TicketFragment
    private lateinit var likedFragment: LikedFragment



    companion object {
        lateinit var viewModel: MovieViewModel
        enum class ScreenTypes {
            Home,
            Liked,
            Ticket,
            Profile
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewModel = viewModels<MovieViewModel> {
            MovieItemModelFactory((application as DatabaseApplication).repository)
        }.value

        homeFragment = HomeFragment(this)
        ticketFragment = TicketFragment(this)
        likedFragment = LikedFragment(this)

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

//        val scope = CoroutineScope(EmptyCoroutineContext)
//        scope.launch {
//            viewModel.addMovie(MovieModel(0, "f", "f", "1000", listOf(), listOf(), "0", "0", true))
//        }



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



}
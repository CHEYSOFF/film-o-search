package cheysoff.film_o_search

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cheysoff.film_o_search.data.database.DatabaseApplication
import cheysoff.film_o_search.data.database.MovieItemModelFactory
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.ui.ChooseBar
import cheysoff.film_o_search.ui.SearchBar
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.LikedFragment
import cheysoff.film_o_search.ui.fragments.SearchFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment
import com.facebook.shimmer.ShimmerFrameLayout

class MainActivity : AppCompatActivity() {

    private lateinit var searchBar: SearchView

    private lateinit var homeButton: ImageButton
    private lateinit var likedButton: ImageButton
    private lateinit var ticketButton: ImageButton
    private lateinit var searchButton: ImageButton

    private lateinit var homeFragment: HomeFragment
    private lateinit var ticketFragment: TicketFragment
    private lateinit var likedFragment: LikedFragment
    private lateinit var searchFragment: SearchFragment


    companion object {
        lateinit var viewModel: MovieViewModel
        lateinit var shimmerContainer: ShimmerFrameLayout
        lateinit var chooseBar: ChooseBar
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
        searchFragment = SearchFragment(this)

        homeButton = findViewById(R.id.homeButton)
        likedButton = findViewById(R.id.likedButton)
        ticketButton = findViewById(R.id.ticketButton)
        searchButton = findViewById(R.id.searchButton)

        searchBar = findViewById(R.id.search_bar_id)

        //        TODO: FIX SHIMMERS
        shimmerContainer = findViewById<View>(R.id.shimmer_view_container) as ShimmerFrameLayout
        shimmerContainer.visibility = View.GONE
//        shimmerContainer.startShimmer();

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, homeFragment)
            commit()
        }



        chooseBar = ChooseBar(
            homeButton,
            likedButton,
            ticketButton,
            searchButton,
            supportFragmentManager,
            homeFragment,
            ticketFragment,
            likedFragment,
            searchFragment
        )
        SearchBar(this, searchFragment, searchBar)


    }

}
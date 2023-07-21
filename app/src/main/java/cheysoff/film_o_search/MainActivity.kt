package cheysoff.film_o_search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cheysoff.film_o_search.data.database.DatabaseApplication
import cheysoff.film_o_search.data.database.MovieItemModelFactory
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.databinding.ActivityMainBinding
import cheysoff.film_o_search.ui.ChooseBar
import cheysoff.film_o_search.ui.SearchBar
import cheysoff.film_o_search.ui.fragments.HomeFragment
import com.facebook.shimmer.ShimmerFrameLayout

class MainActivity : AppCompatActivity() {

    private lateinit var searchBar: SearchView



    private lateinit var binding: ActivityMainBinding


    companion object {
        lateinit var viewModel: MovieViewModel
        lateinit var shimmerContainer: ShimmerFrameLayout
        lateinit var chooseBar: ChooseBar
        lateinit var memoryLeak: Context
        enum class ScreenTypes {
            Home,
            Liked,
            Ticket,
            Search
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        memoryLeak = this
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = viewModels<MovieViewModel> {
            MovieItemModelFactory((application as DatabaseApplication).repository)
        }.value

        val homeButton = binding.chooseBarId.homeButton
        val likedButton = binding.chooseBarId.likedButton
        val ticketButton = binding.chooseBarId.ticketButton
        val searchButton = binding.chooseBarId.searchButton
        searchBar = findViewById(R.id.search_bar_id)

        //        TODO: FIX SHIMMERS
        shimmerContainer = findViewById<View>(R.id.shimmer_view_container) as ShimmerFrameLayout
        shimmerContainer.visibility = View.GONE
//        shimmerContainer.startShimmer();

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, HomeFragment())
            commit()
        }



        chooseBar = ChooseBar(
            homeButton,
            likedButton,
            ticketButton,
            searchButton,
            supportFragmentManager
        )
        SearchBar(this, searchBar)


    }

}
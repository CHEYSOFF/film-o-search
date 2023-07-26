package cheysoff.film_o_search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import cheysoff.film_o_search.data.database.DatabaseApplication
import cheysoff.film_o_search.data.database.MovieItemModelFactory
import cheysoff.film_o_search.data.database.MovieViewModel
import cheysoff.film_o_search.databinding.ActivityMainBinding
import cheysoff.film_o_search.ui.FragmentController
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.LikedFragment
import cheysoff.film_o_search.ui.fragments.SearchFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment
import com.facebook.shimmer.ShimmerFrameLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    companion object {
        lateinit var shimmerContainer: ShimmerFrameLayout
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
        setContentView(binding.root)
        supportActionBar?.hide()

        val viewModel = viewModels<MovieViewModel> {
            MovieItemModelFactory((application as DatabaseApplication).repository)
        }.value

        //        TODO: FIX SHIMMERS
        shimmerContainer = findViewById<View>(R.id.shimmer_view_container) as ShimmerFrameLayout
        shimmerContainer.visibility = View.GONE
//        shimmerContainer.startShimmer();

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, HomeFragment())
            commit()
        }



        setNavigationBar()
        setSearchBar()


    }

    private fun setNavigationBar() {
        val homeButton = binding.chooseBarId.homeButton
        val likedButton = binding.chooseBarId.likedButton
        val ticketButton = binding.chooseBarId.ticketButton
        val searchButton = binding.chooseBarId.searchButton

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

        val homeButton = binding.chooseBarId.homeButton
        val likedButton = binding.chooseBarId.likedButton
        val ticketButton = binding.chooseBarId.ticketButton
        val searchButton = binding.chooseBarId.searchButton

        val fragment: androidx.fragment.app.Fragment
//        TODO: CHANGE TO STATES
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

    private fun setSearchBar() {
        val searchBar = binding.searchBar.searchBarId
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, (FragmentController.getFragment(ScreenTypes.Search) as SearchFragment))
                    commit()
                }
                changeScreen(ScreenTypes.Search)
                (FragmentController.getFragment(ScreenTypes.Search) as SearchFragment).setKeyword(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}
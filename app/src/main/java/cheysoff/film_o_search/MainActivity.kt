package cheysoff.film_o_search

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.data.api.Common
import cheysoff.film_o_search.data.api.TopMoviesResponse
import cheysoff.film_o_search.data.api.models.MovieModel
import cheysoff.film_o_search.data.api.retrofit.RetrofitServices
import cheysoff.film_o_search.ui.ChooseBar
import cheysoff.film_o_search.ui.MovieAdapter
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var searchBar: SearchView

    private lateinit var homeButton: ImageButton
    private lateinit var likedButton: ImageButton
    private lateinit var ticketButton: ImageButton
    private lateinit var profileButton: ImageButton

    private lateinit var homeFragment: HomeFragment
    private lateinit var ticketFragment: TicketFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        homeFragment = HomeFragment(this)
        ticketFragment = TicketFragment(this)

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
            ticketFragment
        )



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
        enum class screenTypes {
            home,
            liked,
            ticket,
            profile
        }
    }

}
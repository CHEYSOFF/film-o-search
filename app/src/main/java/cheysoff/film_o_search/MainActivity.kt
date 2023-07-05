package cheysoff.film_o_search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cheysoff.film_o_search.data.Movie
import cheysoff.film_o_search.databinding.ActivityMainBinding
import cheysoff.film_o_search.ui.MovieAdapter
import java.net.URL

class MainActivity : AppCompatActivity() {
    //    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesList: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView)
        val tmpMovie = Movie(
            0,
            "Sweet New Film",
            "An actual movie descriptionAn actual movie description",
            URL("https://images.unsplash.com/photo-1554080353-a576cf803bda?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cGhvdG98ZW58MHx8MHx8fDA%3D&w=1000&q=80"),
            true
        )
        val tmpMovie2 = Movie(
            0,
            "Harry Potter",
            "An actual movie description",
            URL("https://images.unsplash.com/photo-1554080353-a576cf803bda?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cGhvdG98ZW58MHx8MHx8fDA%3D&w=1000&q=80"),
            false
        )
        moviesList = arrayListOf(tmpMovie, tmpMovie2)
        adapter = MovieAdapter(this, moviesList)

        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.adapter = adapter
    }

}

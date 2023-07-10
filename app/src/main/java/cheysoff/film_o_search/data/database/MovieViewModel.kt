package cheysoff.film_o_search.data.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cheysoff.film_o_search.data.models.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MoviesRepository) : ViewModel() {

    var readAllData: LiveData<List<MovieModel>> = repository.readAllData


    fun addMovie(movie: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("2", "added")
            repository.addMovie(movie)
        }
    }

//    fun getAllMovies(): LiveData<List<MovieModel>> {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getAllMovies()
//            readAllData = repository.readAllData
//        }
//        return readAllData
//    }

}
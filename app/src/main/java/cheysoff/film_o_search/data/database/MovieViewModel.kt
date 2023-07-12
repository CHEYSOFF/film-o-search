package cheysoff.film_o_search.data.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun deleteMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMovie(id)
        }
    }

    fun isLiked(id: Int): MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(repository.isLiked(id))
        }
        return result
    }

}
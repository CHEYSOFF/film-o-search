package cheysoff.film_o_search.ui

import android.widget.SearchView
import cheysoff.film_o_search.MainActivity
import cheysoff.film_o_search.MainActivity.Companion.ScreenTypes
import cheysoff.film_o_search.MainActivity.Companion.chooseBar
import cheysoff.film_o_search.R
import cheysoff.film_o_search.ui.fragments.SearchFragment

class SearchBar(
    activity: MainActivity,
    searchFragment: SearchFragment,
    searchBar: SearchView
) {
    init {
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, searchFragment)
                    commit()
                }
                searchFragment.setKeyword(query)
                chooseBar.changeScreen(ScreenTypes.Profile)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                activity.supportFragmentManager.beginTransaction().apply {
//                    replace(R.id.flFragment, searchFragment)
//                    commit()
//                }
//                searchFragment.setKeyword(newText)
//                chooseBar.changeScreen(ScreenTypes.Profile)
                return false
            }
        })

    }

}
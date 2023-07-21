package cheysoff.film_o_search.ui

import android.util.Log
import android.widget.SearchView
import cheysoff.film_o_search.MainActivity
import cheysoff.film_o_search.MainActivity.Companion.ScreenTypes
import cheysoff.film_o_search.MainActivity.Companion.chooseBar
import cheysoff.film_o_search.R
import cheysoff.film_o_search.ui.fragments.SearchFragment

class SearchBar(
    activity: MainActivity,
    searchBar: SearchView
) {
    init {
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                val frag = SearchFragment()
                activity.supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, (FragmentController.getFragment(ScreenTypes.Search) as SearchFragment))
                    commit()
                }
                Log.d("q", query.orEmpty())
//                frag.setKeyword(query)
                chooseBar.changeScreen(ScreenTypes.Search)
                (FragmentController.getFragment(ScreenTypes.Search) as SearchFragment).setKeyword(query)

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
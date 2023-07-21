package cheysoff.film_o_search.ui

import androidx.fragment.app.Fragment
import cheysoff.film_o_search.MainActivity.Companion.ScreenTypes
import cheysoff.film_o_search.ui.fragments.HomeFragment
import cheysoff.film_o_search.ui.fragments.LikedFragment
import cheysoff.film_o_search.ui.fragments.SearchFragment
import cheysoff.film_o_search.ui.fragments.TicketFragment

object FragmentController {
    var homeFragment: HomeFragment? = null
    var likedFragment: LikedFragment? = null
    var searchFragment: SearchFragment? = null
    var ticketFragment: TicketFragment? = null

    fun getFragment(fragmentType: ScreenTypes): Fragment {
        when (fragmentType) {
            ScreenTypes.Home -> if (homeFragment == null) {
                homeFragment = HomeFragment()
            }

            ScreenTypes.Liked -> if (likedFragment == null) {
                likedFragment = LikedFragment()
            }

            ScreenTypes.Ticket -> if (ticketFragment == null) {
                ticketFragment = TicketFragment()
            }

            ScreenTypes.Search -> if (searchFragment == null) {
                searchFragment = SearchFragment()
            }
        }

        return when (fragmentType) {
            ScreenTypes.Home -> homeFragment!!
            ScreenTypes.Liked -> likedFragment!!
            ScreenTypes.Ticket -> ticketFragment!!
            ScreenTypes.Search -> searchFragment!!
        }
    }
}
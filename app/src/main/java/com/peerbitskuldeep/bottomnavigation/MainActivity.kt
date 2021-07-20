package com.peerbitskuldeep.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.peerbitskuldeep.bottomnavigation.fragments.FirstFragment
import com.peerbitskuldeep.bottomnavigation.fragments.MyProfileFragment
import com.peerbitskuldeep.bottomnavigation.fragments.SecondFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentList: ArrayList<Fragment> = arrayListOf()

    private fun init()
    {
        if (fragmentList.isEmpty())
        {
            fragmentList.add(FirstFragment.newInstance())
            fragmentList.add(MyProfileFragment.newInstance())
            fragmentList.add(SecondFragment.newInstance())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        bottomNavView.background = null

        //dummy menu
        bottomNavView.menu.getItem(1).isEnabled = false

        currentFrag(0,fragmentList)

        bottomNavView.setOnNavigationItemSelectedListener{

            when(it.itemId)
            {
                R.id.menu_dashboard ->  { currentFrag(0,fragmentList)
                                           bottomNavView.menu.getItem(0).setCheckable(true) }
                R.id.menu_setting -> { currentFrag(2,fragmentList)
                                           bottomNavView.menu.getItem(2).setCheckable(true) }

            }
            true
        }

        fbtn.setOnClickListener {
            currentFrag(1,fragmentList)
            bottomNavView.menu.getItem(0).setCheckable(false)
            bottomNavView.menu.getItem(2).setCheckable(false)
        }

    }

    private fun currentFrag(numberOfFrag: Int, fragList: ArrayList<Fragment>) {

        supportFragmentManager.beginTransaction().apply {

            replace(R.id.frame_layout, fragList.get(numberOfFrag))
//            addToBackStack(fragmentList.get(numberOfFrag).javaClass.name)
            commit()

        }
    }

    override fun onBackPressed() {

        if (bottomNavView.selectedItemId == R.id.menu_dashboard)
        {
            super.onBackPressed()
            finish()
        }

        else
        {
            bottomNavView.selectedItemId = R.id.menu_dashboard
        }
    }
}
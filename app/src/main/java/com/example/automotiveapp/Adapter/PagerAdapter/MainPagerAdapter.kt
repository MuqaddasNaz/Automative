package com.example.automotiveapp.Adapter.PagerAdapter

import com.example.automotiveapp.Fragment.HomeFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.automotiveapp.Fragment.RentACarFragment
import com.example.automotiveapp.Fragment.ProfileFragment
import com.example.automotiveapp.Fragment.BuyACarFragment

class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {

        if (position==0){

            return HomeFragment()

        }else if (position==1){

            return  BuyACarFragment()

        }else if (position==2){

            return  RentACarFragment()

        }else if (position==3){

            return ProfileFragment()
        }

        return HomeFragment()


    }


}
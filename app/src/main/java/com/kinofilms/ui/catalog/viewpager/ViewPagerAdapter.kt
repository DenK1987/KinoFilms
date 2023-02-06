package com.kinofilms.ui.catalog.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kinofilms.ui.catalog.CatalogFragment

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private var listDocumentsType = listOf<Int>()

    fun setItems(items: List<Int>) {
        listDocumentsType = items
    }

    override fun getItemCount(): Int = listDocumentsType.size

    override fun createFragment(position: Int): Fragment {
        return listDocumentsType[position].let {
            val fragment = CatalogFragment.newInstance(it)
            fragment
        }
    }
}
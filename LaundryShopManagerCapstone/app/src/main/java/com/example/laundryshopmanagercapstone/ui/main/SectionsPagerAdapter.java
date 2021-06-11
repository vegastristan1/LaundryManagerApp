package com.example.laundryshopmanagercapstone.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.laundryshopmanagercapstone.R;
import com.example.laundryshopmanagercapstone.ui.tabbedProductItem.AllProductListFragment;
import com.example.laundryshopmanagercapstone.ui.tabbedProductItem.BasicProductListFragment;
import com.example.laundryshopmanagercapstone.ui.tabbedProductItem.DryCleanProductListFragment;
import com.example.laundryshopmanagercapstone.ui.tabbedProductItem.SpecialItemProductListFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new AllProductListFragment();
                break;
            case 1:
                fragment = new BasicProductListFragment();
                break;
            case 2:
                fragment = new DryCleanProductListFragment();
                break;
            case 3:
                fragment = new SpecialItemProductListFragment();
                break;
        }
        //return PlaceholderFragment.newInstance(position + 1);
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 4;
    }
}
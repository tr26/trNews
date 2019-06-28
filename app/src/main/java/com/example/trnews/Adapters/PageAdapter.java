package com.example.trnews.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.trnews.Fragments.ChooseTopicFragment;
import com.example.trnews.Fragments.MostPopularFragment;
import com.example.trnews.Fragments.TopStoriesFragment;

public class PageAdapter extends FragmentPagerAdapter {



    public PageAdapter(FragmentManager mgr, int[] colors) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return ChooseTopicFragment.newInstance();

                default:
                    return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "TOP STORIES";
            case 1:
                return "MOST POPULAR";
            case 2:
                return "CHOOSEN TOPIC";

            default:
                return null;
        }
    }

}

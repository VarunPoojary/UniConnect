package com.example.mitadttrial.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mitadttrial.Fragments.CalendarFragment;
import com.example.mitadttrial.Fragments.DashboardFragment;
import com.example.mitadttrial.Fragments.FeedbackFragment;
import com.example.mitadttrial.Fragments.MessagingFragment;

public class FragmentsAdapter extends FragmentPagerAdapter
{

    public FragmentsAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0: return new DashboardFragment();
            case 1: return new MessagingFragment();
            case 2: return new CalendarFragment();
            case 3: return new FeedbackFragment();
            default: return new DashboardFragment();
        }
    }

    @Override
    public int getCount()
    {
        return 4;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if(position == 0)
        {
            title = "DashBoard";
        }
        if(position == 1)
        {
            title = "Messaging";
        }
        if(position == 2)
        {
            title = "Calendar";
        }
        if(position == 3)
        {
            title = "Feedback";
        }
        return title;
    }
}

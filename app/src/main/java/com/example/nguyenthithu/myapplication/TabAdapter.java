package com.example.nguyenthithu.myapplication;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabAdapter.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabAdapter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    //private Context context;
    public TabAdapter(FragmentManager fm) {
        super(fm);
       // this.context = context;
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*
    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tabTextView = view.findViewById(R.id.tabTextView);
        tabTextView.setText(mFragmentTitleList.get(position));
        tabTextView.setTextSize(26); // for big text, increase text size
        tabTextView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        return view;
    }
    */

}

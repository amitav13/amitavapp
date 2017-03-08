package com.ecspan.amitavapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ImagesFragment extends Fragment{
    public static final String TAG = ImagesFragment.class.getSimpleName();
    ViewPager testViewPager;
    TestPagerAdapter adapter;
    private List<String> urls;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.images_layout,container,false);
        testViewPager = (ViewPager) rootView.findViewById(R.id.imagesViewPager);
        initUrls();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        testViewPager.setAdapter(new TestPagerAdapter(getActivity(),urls));
    }

    private void initUrls() {
        urls = new ArrayList<String>();
        urls.add("http://www.ecspan.com/amu/0715160955.jpg");
        urls.add("http://www.ecspan.com/amu/1216151119.jpg");
        urls.add("http://www.ecspan.com/amu/0212161412a.jpg");
        urls.add("http://www.ecspan.com/amu/0212161413.jpg");
        urls.add("http://www.ecspan.com/amu/0212161429.jpg");
        urls.add("http://www.ecspan.com/amu/0212161430.jpg");
        urls.add("http://www.ecspan.com/amu/0212161430a.jpg");
        urls.add("http://www.ecspan.com/amu/0225161314.jpg");
        urls.add("http://www.ecspan.com/amu/0304150922.jpg");
        urls.add("http://www.ecspan.com/amu/0401152014a.jpg");
        urls.add("http://www.ecspan.com/amu/0401152014.jpg");
        urls.add("http://www.ecspan.com/amu/0401152013.jpg");
        urls.add("http://www.ecspan.com/amu/0401151105.jpg");
        urls.add("http://www.ecspan.com/amu/0401151050b.jpg");
        urls.add("http://www.ecspan.com/amu/0401151050.jpg");
        urls.add("http://www.ecspan.com/amu/0401151050a.jpg");
        urls.add("http://www.ecspan.com/amu/0401151049.jpg");
        urls.add("http://www.ecspan.com/amu/0304152053a.jpg");
        urls.add("http://www.ecspan.com/amu/0304152053.jpg");
        urls.add("http://www.ecspan.com/amu/0304152040b.jpg");
        urls.add("http://www.ecspan.com/amu/0304152027.jpg");
        urls.add("http://www.ecspan.com/amu/0304152026a.jpg");
        urls.add("http://www.ecspan.com/amu/0304152026.jpg");
        urls.add("http://www.ecspan.com/amu/0304151437.jpg");
        urls.add("http://www.ecspan.com/amu/0304151118a.jpg");
        urls.add("http://www.ecspan.com/amu/0304150941.jpg");
        urls.add("http://www.ecspan.com/amu/0304150929a.jpg");
        urls.add("http://www.ecspan.com/amu/0304150929.jpg");
        urls.add("http://www.ecspan.com/amu/0402151306b.jpg");
        urls.add("http://www.ecspan.com/amu/0402151956.jpg");
        urls.add("http://www.ecspan.com/amu/0406161122d.jpg");
        urls.add("http://www.ecspan.com/amu/0610161406.jpg");
        urls.add("http://www.ecspan.com/amu/0621161722.jpg");
        urls.add("http://www.ecspan.com/amu/0621161723a.jpg");
        urls.add("http://www.ecspan.com/amu/0812151026b.jpg");
        urls.add("http://www.ecspan.com/amu/IMG_0426.jpg");
        urls.add("http://www.ecspan.com/amu/IMG_0438-COLLAGE.jpg");
        urls.add("http://www.ecspan.com/amu/IMG_0679.jpg");
        urls.add("http://www.ecspan.com/amu/amitav-card.jpg");
        urls.add("http://www.ecspan.com/amu/IMG_0945.jpg");
        urls.add("http://www.ecspan.com/amu/IMG_0947.jpg");
        urls.add("http://www.ecspan.com/amu/IMG_1450318863178.jpg");
    }
}

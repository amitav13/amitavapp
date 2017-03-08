package com.ecspan.amitavapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TestPagerAdapter extends PagerAdapter{

    public List<TestImage> imagesList;
    private Context context;

    public TestPagerAdapter(Context context,List<String> imageUrlsList) {
        this.context = context;
        this.imagesList = new ArrayList<TestImage>();
        for(String imageUrl:imageUrlsList) {
            TestImage image = new TestImage();
            image.url = imageUrl;
            this.imagesList.add(image);
        }
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TestImage image = imagesList.get(position);
        image.view = new ImageView(context);
        Picasso.with(context).load(image.url).into(image.view);
        container.addView(image.view);
        return image;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        TestImage image = imagesList.get(position);
        container.removeView(image.view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == ((TestImage) object).view);
    }

    private static class TestImage {
        ImageView view;
        String url;
    }
}

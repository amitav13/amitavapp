/* AmitavApp by Michael Fallon    mhfallon@bu.edu
 * 
 * This project was created for Amitav Khandewal
 *
 *  Credit is given wherever possible to sources of learning or code.
*/

package com.ecspan.amitavapp;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    // Lateral or Horizontal Navigation with help from http://developer.android.com/training/implementing-navigation/lateral.html
    // Sets up the fragments swiping
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    // Displays the sections for each tab
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get defaults and preferences for Settings
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.

            // Refer to http://developer.android.com/reference/android/support/v4/view/ViewPager.html

            actionBar.addTab(
                    actionBar.newTab()

                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    // Show the ICE contact list
                    return new ICEFragment();

                case 1:
                    // Show the list of DEFCON contacts
                    return new DefFragment();

                case 2:
                    // Show the Black Book contact list
                    return new BlackBFragment();

                case 3:
                    // Show the Workout list
                    return new WorkoutFragment();

                case 4:
                    // Show the tab featuring the picture Album
                    return new ImagesFragment();


                default:
                    // The ICE tab should be the default one displayed if not identified.
                    return new ICEFragment();

            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ICE";
                case 1:
                    return "AVOID";
                case 2:
                    return "BLACK BOOK";
                case 3:
                    return "WORKOUT";
                case 4:
                    return "ALBUM";

                default:
                    return "Section " + (position + 1);
            }

        }
    }

    public static class ICEFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_ice, container, false);

            // Store ICE contacts string into an Array
            final String[] nameICE = getResources().getStringArray(R.array.ICE);
            final int[] imageICE = {R.drawable.chandra, R.drawable.fawad, R.drawable.basanta, R.drawable.renate, R.drawable.mel};
            final TextView selectedOpt = (TextView) rootView.findViewById(R.id.selectedopt);

            ListView iceList = (ListView) rootView.findViewById(R.id.ice_list);

            ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), nameICE, imageICE);
            iceList.setAdapter(listViewAdapter);

/*            iceList.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent intent = new Intent(getActivity(), BooksActivity.class);
                    intent.putExtra("Author", (nameICE[position]));
                    startActivity(intent);
                }
            });*/

            return rootView;
        }
    }
// ListViewAdapter courtesy of Ravi Govarthanan!
// This adaptor will work for both the ICE and AVOID tabs which are setup
// similarly in ice_row.xml and def_row.xml
    public static class ListViewAdapter extends BaseAdapter {
        private Context context;
        List<DataModel> dataModelList = new ArrayList<DataModel>();

        ListViewAdapter(Context context, String[] nameIce, int[] imageIce) {
            for (int i = 0; i < nameIce.length; i++) {
                dataModelList.add(new DataModel(nameIce[i], imageIce[i]));
            }
            this.context = context;
        }

        @Override
        public int getCount() {
            return dataModelList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.ice_row, viewGroup, false);
            ImageView iceImage = (ImageView) view1.findViewById(R.id.imageView);
            TextView nameText = (TextView) view1.findViewById(R.id.name_ice_text);

            DataModel dataModel = dataModelList.get(i);
            iceImage.setImageResource(dataModel.getImageIce());
            nameText.setText(dataModel.getNameIce());
            // I took out the strike thru because I thought it looked better without it
            // nameText.setPaintFlags(nameText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            return view1;
        }

        class DataModel {
            String nameIce;
            int imageIce;

            DataModel(String nameIce, int imageIce) {
                this.nameIce = nameIce;
                this.imageIce = imageIce;
            }

            public String getNameIce() {
                return nameIce;
            }

            public int getImageIce() {
                return imageIce;
            }
        }
    }

    public static class DefFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_def, container, false);

            // Store DEFCON strings into an Array
            final String[] nameDef = getResources().getStringArray(R.array.DEFCON);
            final int[] imageDef = {R.drawable.farid, R.drawable.ritesh, R.drawable.headfoto, R.drawable.vortex, R.drawable.cersei};
            final TextView selectedOpt = (TextView) rootView.findViewById(R.id.selectedopt);
            ListView defList = (ListView) rootView.findViewById(R.id.defcon_list);
            ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), nameDef, imageDef);
            defList.setAdapter(listViewAdapter);

/*
            defList.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    // selectedOpt.setText("You have selected "+writers[position]);
                    Intent intent = new Intent(getActivity(), BooksActivity.class);
                    intent.putExtra("Author", (nameDef[position]));
                    startActivity(intent);
                }
            });
*/

            return rootView;
        }
    }

    public static class ListViewAdapter1 extends BaseAdapter {
        private Context context;
        List<DataModel> dataModelList = new ArrayList<DataModel>();

        ListViewAdapter1(Context context, String[] nameDef, int[] imageDef) {
            for (int i = 0; i < nameDef.length; i++) {
                dataModelList.add(new DataModel(nameDef[i], imageDef[i]));
            }
            this.context = context;
        }

        @Override
        public int getCount() {
            return dataModelList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.def_row, viewGroup, false);
            ImageView iceImage = (ImageView) view1.findViewById(R.id.imageView);
            TextView nameText = (TextView) view1.findViewById(R.id.name_def_text);
            DataModel dataModel = dataModelList.get(i);
            iceImage.setImageResource(dataModel.getImageDef());
            nameText.setText(dataModel.getNameDef());
            return view1;
        }

        class DataModel {
            String nameDef;
            int imageDef;

            DataModel(String nameDef, int imageDef) {
                this.nameDef = nameDef;
                this.imageDef = imageDef;
            }

            public String getNameDef() {
                return nameDef;
            }

            public int getImageDef() {
                return imageDef;
            }
        }
    }
    public static class BlackBFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_blackb, container, false);

            // Store Black Books contacts into an Array
            final String[] nameBlackb = getResources().getStringArray(R.array.BlackBook);
            final int[] imageBlackb = {R.drawable.bb1, R.drawable.bb2, R.drawable.bb3, R.drawable.bb4, R.drawable.bb5, R.drawable.bb6, R.drawable.bb7, R.drawable.bb8, R.drawable.bb9, R.drawable.bb10, R.drawable.bb11, R.drawable.bb12, R.drawable.bb13, R.drawable.bb14, R.drawable.bb15, R.drawable.bb16};
            final int[] imageStars = {R.drawable.fourstars,R.drawable.fourstars,R.drawable.fourstars,R.drawable.threestars,R.drawable.fourstars,R.drawable.twostars,R.drawable.fourstars,R.drawable.threestars,R.drawable.fourstars,R.drawable.fourstars,R.drawable.fourstars,R.drawable.threestars,R.drawable.fourstars,R.drawable.twostars,R.drawable.fourstars,R.drawable.threestars};
            final TextView selectedOpt = (TextView) rootView.findViewById(R.id.selectedopt);
            ListView blackbList = (ListView) rootView.findViewById(R.id.blackb_list);
            ListViewAdapter2 listViewAdapter = new ListViewAdapter2(getActivity(), nameBlackb, imageBlackb, imageStars);
            blackbList.setAdapter(listViewAdapter);
            blackbList.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    selectedOpt.setText("Contact "+nameBlackb[position]+" for a date?");

                    /*Intent intent = new Intent(getActivity(), BooksActivity.class);
                    intent.putExtra("Author", (nameBlackb[position]));
                    startActivity(intent);*/
                }
            });

            return rootView;
        }
    }

    // Use this adaptor for blackb_row.xml and workout_row.xml
    public static class ListViewAdapter2 extends BaseAdapter {
        private Context context;
        List<DataModel> dataModelList = new ArrayList<DataModel>();

        ListViewAdapter2(Context context, String[] nameBlackb, int[] imageBlackb, int[] imageStars) {
            for (int i = 0; i < nameBlackb.length; i++) {
                dataModelList.add(new DataModel(nameBlackb[i], imageBlackb[i], imageStars[i]));
            }
            this.context = context;
        }

        @Override
        public int getCount() {
            return dataModelList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.blackb_row, viewGroup, false);
            ImageView bbImage = (ImageView) view1.findViewById(R.id.imageView);
            ImageView starsImage = (ImageView) view1.findViewById(R.id.imageView2);
            TextView nameText = (TextView) view1.findViewById(R.id.name_blackb_text);
            DataModel dataModel = dataModelList.get(i);
            bbImage.setImageResource(dataModel.getImageBlackb());
            nameText.setText(dataModel.getNameBlackb());
            starsImage.setImageResource(dataModel.getImageStars());
            return view1;
        }

        class DataModel {
            String nameBlackb;
            int imageBlackb;
            int imageStars;

            DataModel(String nameBlackb, int imageBlackb, int imageStars) {
                this.nameBlackb = nameBlackb;
                this.imageBlackb = imageBlackb;
                this.imageStars = imageStars;
            }

            public String getNameBlackb() {
                return nameBlackb;
            }

            public int getImageBlackb() {
                return imageBlackb;
            }

            public int getImageStars(){
                return imageStars;
            }
        }
    }

    public static class WorkoutFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_workout, container, false);

            // Store daily Workout activities into an Array
            final String[] nameWorkout = getResources().getStringArray(R.array.Workout);
            final int[] imageWork = {R.drawable.weight5, R.drawable.weight3, R.drawable.weight4, R.drawable.the_rock, R.drawable.weight8, R.drawable.weight1, R.drawable.john_cena, R.drawable.weight6, R.drawable.weight9, R.drawable.the_thing, R.drawable.weight5, R.drawable.weight3, R.drawable.weight4, R.drawable.hulk, R.drawable.weight8, R.drawable.weight1};
            final int[] imageStars = {R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.unchecked,R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.checkbox,R.drawable.unchecked, R.drawable.checkbox};
            final TextView selectedOpt = (TextView) rootView.findViewById(R.id.selectedopt);
            ListView workoutList = (ListView) rootView.findViewById(R.id.workout_list);
            ListViewAdapter2 listViewAdapter = new ListViewAdapter2(getActivity(), nameWorkout, imageWork, imageStars);
            workoutList.setAdapter(listViewAdapter);
            workoutList.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    selectedOpt.setText("Change completion status for "+nameWorkout[position]+"?");
                    /*Intent intent = new Intent(getActivity(), BooksActivity.class);
                    intent.putExtra("Author", (nameWorkout[position]));
                    startActivity(intent);*/
                }
            });

            return rootView;
        }
    }

    // Use this adaptor for displaying images on the Album tab album_row.xml
    public static class ListViewAdapter3 extends BaseAdapter {
        private Context context;
        List<DataModel> dataModelList = new ArrayList<DataModel>();

        ListViewAdapter3(Context context, int[] imageAlbum) {
            for (int i = 0; i < imageAlbum.length; i++) {
                dataModelList.add(new DataModel(imageAlbum[i]));
            }
            this.context = context;
        }

        @Override
        public int getCount() {
            return dataModelList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view1 = inflater.inflate(R.layout.album_row, viewGroup, false);
            ImageView albumImage = (ImageView) view1.findViewById(R.id.iv);
            DataModel dataModel = dataModelList.get(i);
            albumImage.setImageResource(dataModel.getImageAlbum());
            return view1;
        }

        class DataModel {
            int imageAlbum;

            DataModel(int imageAlbum) {
                this.imageAlbum = imageAlbum;
            }

            public int getImageAlbum() {
                return imageAlbum;
            }
        }
    }

    // Investigate Picasso - http://square.github.io/picasso/
    // Modify using RecyclerView & Adaptor because it's crashing on the Kindle
    //     https://guides.codepath.com/android/using-the-recyclerview
    //  https://www.intertech.com/Blog/android-v5-lollipop-recyclerview-tutorial/
    public static class AlbumFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_album, container, false);

            // Put the pictures into an Album array
            final int[] imageAlbum = {
                    R.drawable.aa01,
                    R.drawable.aa02,
                    R.drawable.aa03,
                    R.drawable.aa1,
                    R.drawable.aa10,
                    R.drawable.aa11,
                    R.drawable.aa2,
                    R.drawable.aa3,
                    R.drawable.aa4,
                    R.drawable.aa5,
                    R.drawable.aa6,
                    R.drawable.aa7,
                    R.drawable.aa22,
                    R.drawable.aa8,
                    R.drawable.aa9,
                    R.drawable.aa12,
                    R.drawable.aa13,
                    R.drawable.aa14,
                    R.drawable.aa15,
                    R.drawable.aa16,
                    R.drawable.aa17,
                    R.drawable.aa18,
                    R.drawable.aa19,
                    R.drawable.aa20,
                    R.drawable.aa21,
                    R.drawable.aa23,
                    R.drawable.aa24,
                    R.drawable.aa25,
                    R.drawable.aa26,
                    R.drawable.aa27,
                    R.drawable.aa28,
                    R.drawable.aa29,
                    R.drawable.aa30,
                    R.drawable.aa31,
            };
            final TextView selectedOpt = (TextView) rootView.findViewById(R.id.selectedopt);
            ListView albumList = (ListView) rootView.findViewById(R.id.album_list);
            ListViewAdapter3 listViewAdapter = new ListViewAdapter3(getActivity(), imageAlbum);
            albumList.setAdapter(listViewAdapter);
            albumList.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
/*                    Intent intent = new Intent(getActivity(), BooksActivity.class);
                    intent.putExtra("Author", (imageAlbum[position]));
                    startActivity(intent);*/
                }
            });

            return rootView;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.db_menu, menu);
        return true;
    }


}
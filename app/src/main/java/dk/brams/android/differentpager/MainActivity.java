package dk.brams.android.differentpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_page_layout);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
    }



    // Since this is an object collection, use a FragmentStatePagerAdapter,

    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        // When requested, this adapter returns a DemoObjectFragment,
        // representing an object in the collection.
        // and NOT a FragmentPagerAdapter.
        DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
        ViewPager mViewPager;

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }
    }

    // Instances of this class are fragments representing a single
    // object in our collection.
    public static class DemoObjectFragment extends Fragment {
        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // The last two arguments ensure LayoutParams are inflated properly.
            View rootView = inflater.inflate(R.layout.content_main, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(R.id.text1)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }
}
package com.example.amin.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.amin.criminalintent.models.Crime;
import com.example.amin.criminalintent.models.CrimeLab;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity implements ScroolChange{
    private static final String EXTRA_CRIME_ID = "com.example.amin.criminalintent.crime_id";
    private static final String Tag = "CrimePagerActivity";

    public static Intent newIntent(Context context, UUID crimeId) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public void goFirst() {

        mViewPager.setCurrentItem(0);
    }

    @Override
    public void goLaast() {
        mViewPager.setCurrentItem(99);

    }

    private List<Crime> mcrimes;
    public  ViewPager mViewPager;
    public static Button go_first;
    public int test;
    public static Button go_last;
    private int temp = -1;
    public static boolean disableOrWhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = findViewById(R.id.crime_view_pager);
        go_first = findViewById(R.id.go_to_forst);
        go_last = findViewById(R.id.go_to_lost);
//        ViewPager viewPager= new ViewPager(this);
//        setContentView(viewPager);


        mcrimes = CrimeLab.getInstance().getCrimes();
        go_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);

            }
        });
        go_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(99);


            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int last;

            public void onPageScrollStateChanged(int state) {
                if (state == 0 && last == 1) {
                    mViewPager.setCurrentItem(0);
                }
                if (last == 1 && state == 0) {
                    mViewPager.setCurrentItem(99);
                }
                last = state;
//                int temp=mViewPager.getCurrentItem();
//                if(temp<1)
//                {
//                    //intro_images is viewpager
//                    mViewPager.setCurrentItem(99,true);
//                }
//                else if(temp>98)
//                {
//                    //intro_images is viewpager
//                    Toast.makeText(CrimePagerActivity.this,"hello",Toast.LENGTH_SHORT).show();
//                    mViewPager.setCurrentItem(0,true);
//                }


            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            public void onPageSelected(int position) {

                // skip fake page (first), go to last page
//                if (position == -1) {
//                    ((ViewPager) mViewPager).setCurrentItem(99, false);
//                }
//
//                // skip fake page (last), go to first page
//                if (position == 100) {
//                    ((ViewPager) mViewPager).setCurrentItem(0, false); //notice how this jumps to position 1, and not position 0. Position 0 is the fake page!
//                }

//                Toast.makeText(CrimePagerActivity.this,"hello",Toast.LENGTH_SHORT).show();
                if (mViewPager.getCurrentItem() == 0)

                    go_first.setEnabled(false);
                if (mViewPager.getCurrentItem() == 99)

                    go_last.setEnabled(false);
                if (mViewPager.getCurrentItem() != 0)

                    go_first.setEnabled(true);
                if (mViewPager.getCurrentItem() != 99)

                    go_last.setEnabled(true);

//                CrimeDetailFragment crimeDetailFragment= new CrimeDetailFragment();
//                if (mViewPager.getCurrentItem() == 0)
//                   go_to_first.setEnabled(false);
//
//                if (mViewPager.getCurrentItem() == 99)
//                   go_to_last.setEnabled(false);
//
//                if (mViewPager.getCurrentItem() != 0)
//                    go_to_first.setEnabled(true);
//
//                if (mViewPager.getCurrentItem() != 99)
//                    go_to_last.setEnabled(true);
//




            }



        });

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {


                //if you want show diffrent fragment in swipws place swithc statement based on positopn(i)
                Log.d(Tag, "fragment" + i);
                UUID crimeId = mcrimes.get(i).getId();

                return CrimeDetailFragment.newInstance(crimeId);

            }

            @Override
            public int getCount() {
                return mcrimes.size();
            }
        });

        int index = CrimeLab.getInstance().getInexOfCrime(crimeId);
        if (index < 0) {
            index = 0;
        }
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(index);

    }
}
package com.example.amin.criminalintent;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.amin.criminalintent.models.Crime;
import com.example.amin.criminalintent.models.CrimeLab;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeDetailFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crimeId";

    private Crime mCrime;

    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    public static Button go_to_first;
    public static Button go_to_last;
private ScroolChange scroolChange;

    public static CrimeDetailFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeDetailFragment fragment = new CrimeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CrimeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.getInstance().getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime_detail, container, false);

        mTitleField = view.findViewById(R.id.crime_title);
        mDateButton = view.findViewById(R.id.crime_date);
        mSolvedCheckBox = view.findViewById(R.id.crime_solved);

        mDateButton.setEnabled(false);
        mTitleField.setText(mCrime.getTitle());
        mDateButton.setText(mCrime.getDate().toString());
        mSolvedCheckBox.setChecked(mCrime.isSolved());

        go_to_last = view.findViewById(R.id.go_to_last);
        go_to_first = view.findViewById(R.id.go_to_first);

        go_to_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CrimePagerActivity crimePagerActivity = (CrimePagerActivity) getActivity();
//                crimePagerActivity.mViewPager.setCurrentItem(0);


            }
        });
        go_to_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                CrimePagerActivity crimePagerActivity = (CrimePagerActivity) getActivity();
//                crimePagerActivity.mViewPager.setCurrentItem(99);


            }
        });
     ////
        final CrimePagerActivity crimePagerActivity = (CrimePagerActivity) getActivity();
//        crimePagerActivity.mViewPager.setCurrentItem(0);

crimePagerActivity.mViewPager.getCurrentItem();
//CrimePagerActivity.go_first.setEnabled(CrimePagerActivity.disableOrWhat);
//        CrimePagerActivity.go_last.setEnabled(CrimePagerActivity.disableOrWhat);
//        crimePagerActivity.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            public void onPageScrollStateChanged(int state) {
//
//
//            }
//
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            public void onPageSelected(int position) {
//
//                if (crimePagerActivity.mViewPager.getCurrentItem() == 0)
//                    go_to_first.setEnabled(false);
//                Toast.makeText(getActivity(),"hello1",Toast.LENGTH_SHORT).show();
//                if (crimePagerActivity.mViewPager.getCurrentItem() == 99)
//                   go_to_last.setEnabled(false);
//                Toast.makeText(getActivity(),"hello2",Toast.LENGTH_SHORT).show();
//                if (crimePagerActivity.mViewPager.getCurrentItem() != 0)
//                  go_to_first.setEnabled(true);
//                Toast.makeText(getActivity(),"hello3",Toast.LENGTH_SHORT).show();
//                if (crimePagerActivity.mViewPager.getCurrentItem() != 99)
//                   go_to_last.setEnabled(true);
//                Toast.makeText(getActivity(),"hello4",Toast.LENGTH_SHORT).show();
//
//            }
//        });

/////

                mTitleField.addTextChangedListener(new

            TextWatcher() {
                @Override
                public void beforeTextChanged (CharSequence s,int start, int count, int after){

                }

                @Override
                public void onTextChanged (CharSequence s,int start, int before, int count){
                    mCrime.setTitle(s.toString());
                }

                @Override
                public void afterTextChanged (Editable s){

                }
            });

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

            {
                @Override
                public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
                mCrime.setSolved(isChecked);
            }
            });

        return view;
        }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        scroolChange=(ScroolChange)context;
    }

}

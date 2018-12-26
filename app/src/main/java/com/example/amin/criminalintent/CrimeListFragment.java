package com.example.amin.criminalintent;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amin.criminalintent.models.Crime;
import com.example.amin.criminalintent.models.CrimeLab;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    public  int posi;
    public CrimeAdapter  mCrimeAdapter;
    public CrimeListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mRecyclerView = view.findViewById(R.id.crimes_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();

    }


    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.getInstance();
        List<Crime> crimes = crimeLab.getCrimes();
        if (mCrimeAdapter==null) {
            mCrimeAdapter= new CrimeAdapter(crimes);
            mRecyclerView.setAdapter(mCrimeAdapter);
        }else
        {
            //because its singletoon you can cm line below
            mCrimeAdapter.setmCrimes(crimes);
//            mCrimeAdapter.notifyDataSetChanged();

            mCrimeAdapter.notifyItemChanged(posi);
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        private Crime mCrime;

        public CrimeHolder(@NonNull View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.list_item_crime_title);
            mDateTextView = itemView.findViewById(R.id.list_item_crime_date);
            mSolvedImageView = itemView.findViewById(R.id.crime_solved);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posi=getAdapterPosition();
//                    Toast.makeText(getActivity(), mCrime.getTitle() + " clicked!", Toast.LENGTH_LONG).show();
                    Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
                    startActivity(intent);
                }
            });
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(crime.getTitle());
            mDateTextView.setText(crime.getDate().toString());
            mSolvedImageView.setVisibility(crime.isSolved() == true ? View.VISIBLE : View.GONE);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public void setmCrimes(List<Crime> mCrimes) {
            this.mCrimes = mCrimes;
        }

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_crime, parent, false);
            CrimeHolder crimeHolder = new CrimeHolder(view);
            return crimeHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            posi=position;
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

}

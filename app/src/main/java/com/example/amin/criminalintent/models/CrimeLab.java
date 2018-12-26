package com.example.amin.criminalintent.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab instance;
    LinkedHashMap<UUID, Crime> mCrimes2;

//    private List<Crime> mCrimes;

    private CrimeLab() {
        mCrimes2 = new LinkedHashMap<>();
//        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime#" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes2.put(crime.getId(), crime);
//            mCrimes.add(crime);
        }
    }

    public static CrimeLab getInstance() {
        if (instance == null)
            instance = new CrimeLab();

        return instance;
    }

    public List<Crime> getCrimes() {

        List<Crime> valueList = new ArrayList(mCrimes2.values());
        return valueList;
    }

    public Crime getCrime(UUID id) {
//        for (Crime crime : mCrimes2) {
//            if (crime.getId().equals(id))
//                return crime;
//        }
        Crime crime;
        if (mCrimes2.containsKey(id))
            return mCrimes2.get(id);


        return null;
//        crime.getId()

//        return null;
    }

    public int getInexOfCrime(UUID id) {
List<Crime>crimes =getCrimes();

for(int i=0;i<crimes.size();i++){
    if (crimes.get(i).getId().equals(id))
        return i;
}
return -1;
    }
}
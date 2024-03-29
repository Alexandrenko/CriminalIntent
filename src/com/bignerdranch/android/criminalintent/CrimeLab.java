package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class CrimeLab {
	
	private static final String TAG = "CrimeLab";
	private static final String FILENAME = "crimes.json";
	
	private ArrayList<Crime> mCrimes;
	private CriminalIntentJSONSerializer mSerializer;
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	
	private CrimeLab(Context appContext){
		mAppContext = appContext;
		mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME); //need for save files
		
		try {
			mCrimes = mSerializer.loadCrimes();
		} catch (Exception e) {
			mCrimes = new ArrayList<Crime>();
			Log.e(TAG, " Error loading crimes: ", e);
		}
	}
	
	public void addCrime(Crime c){
		mCrimes.add(c);
	}
	
	public void deleteCrime(Crime c){
		mCrimes.remove(c);
	}
	
	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}
	
	public Crime getCrime(UUID id){
		for (Crime c : mCrimes) {
			if (c.getId().equals(id)) 
				return c;
			
		}
		return null;
	}



	public static CrimeLab get(Context c){
		if (sCrimeLab == null) {
			sCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}
	
	public boolean saveCrimes(){
		try {
			mSerializer.saveCrimes(mCrimes);
			Log.d(TAG, "crimes save to file");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			Log.d(TAG, "error saving crimes: " + e);
			return false;
		}
	}
	
	

}

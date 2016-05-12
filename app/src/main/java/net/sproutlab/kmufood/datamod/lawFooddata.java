package net.sproutlab.kmufood.datamod;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kde713 on 2016. 5. 1..
 */
public class lawFooddata {

    private final String PREF_NAME = "net.sproutlab.kmufood";

    static Context mContext;

    private String[] Rule0 = {"baro1","baro2","nood","bap1","bap2","fire1","fire2"};
    private String[] Rule1 = {"mon", "tue", "wed", "thu", "fri"};

    SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy.MM.dd");

    public lawFooddata(Context c){
        mContext = c;
    }

    public void saveMenu(String[][] parsedData){
        SharedPreferences mPref = mContext.getSharedPreferences(PREF_NAME,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor mPrefEditor = mPref.edit();
        for(int i=0; i<7; i++){
            for(int j=0; j<5; j++){
                mPrefEditor.putString("law-"+Rule1[j]+"-"+Rule0[i],parsedData[i][j].replace(" ", "\n"));
                Log.d("lawFoodparser-Data", "Data for key "+"law-"+Rule1[j]+"-"+Rule0[i]+" is "+parsedData[i][j]);
            }
        }
        mPrefEditor.commit();
    }

    public String[][] getMenu(){
        String[][] returnMenu = new String[5][7];
        SharedPreferences mPref = mContext.getSharedPreferences(PREF_NAME,
                Activity.MODE_PRIVATE);
        for(int i=0; i<5; i++){
            for(int j=0; j<7; j++){
                returnMenu[i][j] = mPref.getString("law-"+Rule1[i]+"-"+Rule0[j],"blank");
                Log.d("lawFoodparser-Data", "returned Data for section "+Integer.toString(i)+","+Integer.toString(j)+"(law-"+Rule1[i]+"-"+Rule0[j]+") is "+returnMenu[i][j]);
            }
        }
        return returnMenu;
    }

    public void updateTS(){
        SharedPreferences mPref = mContext.getSharedPreferences(PREF_NAME,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor mPrefEditor = mPref.edit();
        Date mDate = new Date(System.currentTimeMillis());
        mPrefEditor.putString("law-update",mDateFormat.format(mDate));
        mPrefEditor.commit();
    }

    public Boolean checkTS(){
        SharedPreferences mPref = mContext.getSharedPreferences(PREF_NAME,
                Activity.MODE_PRIVATE);
        String update = mPref.getString("law-update", null);
        Calendar curCal = Calendar.getInstance();
        Calendar recCal = Calendar.getInstance();
        if(update == null) return true;
        try {
            recCal.setTime(mDateFormat.parse(update));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int[] chkvar = {curCal.get(Calendar.WEEK_OF_YEAR) - recCal.get(Calendar.WEEK_OF_YEAR)};

        if(chkvar[0] > 1) return true;
        else return false;
    }
}

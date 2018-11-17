package models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gurme L on 3/21/2018.
 */
public class UserData {
    private static UserData ourInstance;
    private SharedPreferences mPrefs;
    private static final String  PREF_IS_LOGGED_IN= "pref_is_logged_in";
    private static final String  PREF_USER_TOKEN= "pref_user_token";
    private static final String PREF_USER_NAME = "pref_username";


    public static UserData getInstance(Context context) {
        if(ourInstance==null){
            ourInstance=new UserData(context);
        }

        return ourInstance;
    }

    private UserData(Context context) {
        this.mPrefs =context.getSharedPreferences("user_data", Context.MODE_PRIVATE);
    }
    public boolean isLoggedIn(){
        return this.mPrefs.getBoolean(PREF_IS_LOGGED_IN, false);
    }
    public  void logout(){
        SharedPreferences.Editor localEditor = this.mPrefs.edit();
        localEditor.putBoolean(PREF_IS_LOGGED_IN, false);
        localEditor.commit();
    }

    public  void login(){
        SharedPreferences.Editor localEditor = this.mPrefs.edit();
        localEditor.putBoolean(PREF_IS_LOGGED_IN, true);
        localEditor.commit();
    }
    public String getToken(){
        return this.mPrefs.getString(PREF_USER_TOKEN,"");
    }
    public void setToken(String token){
        SharedPreferences.Editor localEditor = this.mPrefs.edit();
        localEditor.putString(PREF_USER_TOKEN,token);
        localEditor.commit();
    }
    public String getUsername(){
        return this.mPrefs.getString(PREF_USER_NAME,"");
    }
    public void setUsername(String username){
        SharedPreferences.Editor localEditor = this.mPrefs.edit();
        localEditor.putString(PREF_USER_NAME,username);
        localEditor.commit();
    }
}

package utng.edu.mx.menusarahi.sqlite;

import android.content.Context;
import android.content.SharedPreferences;
import utng.edu.mx.menusarahi.model.User;

/**
 * Created by jony on 19/03/17.
 */

public class Session {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    Context context;
    private User user = new User();




    public Session(Context context){
        this.context=context;
        preferences = context.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    public  void setLoggedin(boolean logeedin){
        editor.putBoolean(user.getId().toString(),logeedin);
        editor.commit();
    }
    public  boolean loggedin(){
        return preferences.getBoolean(user.getId().toString(),false);
    }




}

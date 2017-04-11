package utng.edu.mx.menusarahi.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import utng.edu.mx.menusarahi.model.User;

/**
 * Created by jony on 16/2/2017.
 */

public class DBOperations {
    private static BaseHelper db;
    private static  DBOperations operations;
    private Cursor cursor;
    private User user = new User();
    private Session session;
    public static String idSession;
    public static DBOperations getDBOperations(
            Context context){
        if(operations==null) {
            operations = new DBOperations();
        }
        if(db==null){
            db = new BaseHelper(context);
        }
        return operations;
    }

    public String addUser(User user) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        String idUser = Contract.Users.generateIdUser();
        values.put(Contract.Users.ID, idUser);
        values.put(Contract.Users.USER, user.getUser());
        values.put(Contract.Users.PASSWORD, user.getPassword());
        values.put(Contract.Users.LASTNAME, user.getLastname());
        values.put(Contract.Users.FIRSTNAME, user.getFirstname());
        database.insertOrThrow(BaseHelper.Tablas.USER, null, values);

return idUser;
    }

    public  void Combo(Context context){

            BaseHelper baseHelper = new BaseHelper(context);
            String linea = null;
            user = new User();

            SQLiteDatabase db = baseHelper.getReadableDatabase();
            if (db != null) {
                Cursor c = db.rawQuery("select firsname, lastname from user where id = '" + idSession + "'", null);
                if (c.moveToFirst()) {
                    linea = c.getString(0) + " " + c.getString(1);
                }
                //textView.setText(linea.toString());
            }
            //return linea;
    }
    public Boolean ingresar(Context context, String userName, String password ) {
        SQLiteDatabase database = db.getReadableDatabase();
        session = new Session(context);
        String usuario = userName;
        String contrasenia = password;
        cursor = database.rawQuery("select user,password,id from user where " + "user='" + usuario + "' and password='" + contrasenia + "'", null);


        if(cursor.moveToFirst()){
            String use = cursor.getString(0);
            String pass = cursor.getString(1);

            //user.setId(cursor.getString(2).toString());
            idSession = cursor.getString(2).toString();
            if (usuario.equals(use) && contrasenia.equals(pass)) {
                session.setLoggedin(true);
                return true;
            }
        }
        return false;
    }

}

package utng.edu.mx.menusarahi.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

/**
 * Created by jony on 22/1/2017.
 */

public class BaseHelper extends SQLiteOpenHelper {
    public static final  String DATABASE_NAME = "musica.db";
    private static  final int      CURRENT_VERSION = 1;
    private final Context context;

    interface Tablas {
        String USER ="user";
    }

    public BaseHelper(Context context) {
        super(context, DATABASE_NAME,null, CURRENT_VERSION);
        this.context=context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE " +
                        " %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " %s TEXT NOT NULL UNIQUE," +
                        " %s TEXT NOT NULL, " +
                        " %s INTEGER NOT NULL , " +
                        " %s TEXT NOT NULL, " +
                        " %s TEXT NOT NULL )",
                Tablas.USER, BaseColumns._ID,
                Contract.Users.ID,
                Contract.Users.USER,
                Contract.Users.PASSWORD,
                Contract.Users.FIRSTNAME,
                Contract.Users.LASTNAME
        ));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS "+ Tablas.USER);
    }


}

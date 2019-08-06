package moon.spec7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CertificationDBHelper extends SQLiteOpenHelper{
    static final int version = 1;
    public CertificationDBHelper(Context context)
    {
        super(context, "cerDB.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table cerDB (id integer primary key autoincrement, name text, year text, month text, day text, number text, agency text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS cerDB;");
        onCreate(db);
    }
    public void removeData(int id)
    {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.execSQL("DELETE FROM cerDB WHERE id = " + id + ";");
    }
}
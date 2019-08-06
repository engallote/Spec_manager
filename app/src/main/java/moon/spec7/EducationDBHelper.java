package moon.spec7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EducationDBHelper extends SQLiteOpenHelper{
    static final int version = 1;
    public EducationDBHelper(Context context)
    {
        super(context, "eduDB.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table eduDB (id integer primary key autoincrement, name text, time text, area text, school text, major text, ... submajor text, score text, majorscore text, majorgrade text, grade text, status text, year1 text, month1 text, day1 text, year2 text, month2 text, day2 text, etc text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS eduDB;");
        onCreate(db);
    }
    public void removeData(int id)
    {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.execSQL("DELETE FROM eduDB WHERE id = " + id + ";");
    }
}



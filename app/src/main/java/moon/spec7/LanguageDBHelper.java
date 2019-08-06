package moon.spec7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LanguageDBHelper extends SQLiteOpenHelper{
    static final int version = 1;
    public LanguageDBHelper(Context context)
    {
        super(context, "lanDB.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table lanDB (id integer primary key autoincrement, name text, year1 text, month1 text, day1 text, year2 text, month2 text, day2 text, score text, number text, number2 text, agency text, chk text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS lanDB;");
        onCreate(db);
    }
    public void removeData(int id)
    {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.execSQL("DELETE FROM lanDB WHERE id=" + id + ";");
    }
//    public void removeData(LanguageListItem item)
//    {
//        SQLiteDatabase sdb = getWritableDatabase();
//        sdb.execSQL("DELETE FROM lanDB WHERE name='"+item.getName()+"' AND score='" + item.getScore() + "' AND number='" + item.getNumber() + "';");
//    }
}
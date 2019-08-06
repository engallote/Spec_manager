package moon.spec7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpDBHelper extends SQLiteOpenHelper{
    static final int version = 1;
    public ExpDBHelper(Context context)
    {
        super(context, "expDB.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table expDB (id integer primary key autoincrement, title text, year text, month text, day text, content text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS expDB;");
        onCreate(db);
    }
    public void removeData(int id)
    {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.execSQL("DELETE FROM expDB WHERE id=" + id + ";");
    }
//    public void removeData(ExpListItem item)
//    {
//        SQLiteDatabase sdb = getWritableDatabase();
//        sdb.execSQL("DELETE FROM expDB WHERE title='"+item.getTitle()+"' AND content='"+ item.getContent()+"';");
//    }
}
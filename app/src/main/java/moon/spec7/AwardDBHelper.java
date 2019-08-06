package moon.spec7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AwardDBHelper extends SQLiteOpenHelper{
    static final int version = 1;
    public AwardDBHelper(Context context)
    {
        super(context, "awardDB.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table awardDB (id integer primary key autoincrement, name text, year text, month text, day text, awardname text, agency text, content text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS awardDB;");
        onCreate(db);
    }
//    public void removeData(AwardListItem item)
//    {
//        SQLiteDatabase sdb = getWritableDatabase();
//        sdb.execSQL("delete from awardDB where name='"+item.getName()+"' AND awardname='"+item.getAwardname()+"' AND content='" + item.getContent() + "';");
//    }
    public void removeData(int id)
    {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.execSQL("delete from awardDB where id = " + id + ";");
    }
}
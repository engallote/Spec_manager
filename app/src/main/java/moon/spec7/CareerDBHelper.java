package moon.spec7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CareerDBHelper extends SQLiteOpenHelper{
    static final int version = 1;
    public CareerDBHelper(Context context)
    {
        super(context, "careerDB.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table careerDB (id integer primary key autoincrement, name text, adress text, status text, form text, position1 text, position2 text, task text, year1 text, month1 text, day1 text, year2 text, month2 text, day2 text, etc text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS careerDB;");
        onCreate(db);
    }
//    public void removeData(CareerListItem item)
//    {
//        SQLiteDatabase sdb = getWritableDatabase();
//        String[] date = item.getDate1().split("/");
//        sdb.execSQL("DELETE FROM careerDB WHERE name='"+item.getName()+"' AND year1 = '" + date[0] + "' AND month1 = '" + date[1] + "' AND day1 ='" + date[2] + "' AND task = '" + item.getTask()
//                + "' AND position1='" + item.getPosition1() +"' AND position2='" + item.getPosition2()+"';");
//    }
    public void removeData(int id)
    {
        SQLiteDatabase sdb = getWritableDatabase();
        sdb.execSQL("DELETE FROM careerDB WHERE id = " + id + ";");
    }
}
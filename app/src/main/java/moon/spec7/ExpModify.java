package moon.spec7;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ExpModify extends AppCompatActivity{
    private ExpDBHelper dbHelper = new ExpDBHelper(this);
    EditText title, year, month, day, content;
    int ID;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputexp);
        init();
        intent = getIntent();
        ID = Integer.parseInt(intent.getStringExtra("ID"));
        title.setText(intent.getStringExtra("title"));
        year.setText(intent.getStringExtra("year"));
        month.setText(intent.getStringExtra("month"));
        day.setText(intent.getStringExtra("day"));
        content.setText(intent.getStringExtra("content"));
    }
    public void init()
    {
        title = (EditText) findViewById(R.id.title);
        year = (EditText) findViewById(R.id.year);
        month = (EditText) findViewById(R.id.month);
        day = (EditText) findViewById(R.id.day);
        content = (EditText) findViewById(R.id.content);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.cancel) {
            setResult(RESULT_CANCELED, intent);
            finish();
        }
        else {
            try{
                String title2 = title.getText().toString();
                String year2 = year.getText().toString();
                String month2 = month.getText().toString();
                String day2 = day.getText().toString();
                String content2 = content.getText().toString();

                if(month.getText().toString().length() == 1)
                    month2 = "0" + month.getText().toString();
                else
                    month2 = month.getText().toString();

                if(day.getText().toString().length() == 1)
                    day2 = "0" + day.getText().toString();
                else
                    day2 = day.getText().toString();

                if(title2.length() > 0 && (year2.length() == 4) && (month2.length() == 2) && (day2.length() == 2) && content2.length() > 0)
                {
                    SQLiteDatabase sdb = dbHelper.getWritableDatabase();
                    sdb.execSQL("UPDATE expDB SET title='"+title2+"',year='"+year2+"',month='"+month2+"',day='"+day2+"',content='"+content2+"' WHERE id="+ID+";");
                    Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    if(title2.length() == 0) {
                        title.requestFocus();
                        Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(year2.length() < 4) {
                        year.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(month2.length() == 0) {
                        month.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(day2.length() == 0) {
                        day.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(content2.length() == 0) {
                        content.requestFocus();
                        Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            catch (SQLiteException e)
            {
                Toast.makeText(this, "에러!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
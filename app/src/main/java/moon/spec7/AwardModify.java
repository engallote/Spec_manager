package moon.spec7;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

public class AwardModify extends AppCompatActivity{
    private AwardDBHelper dbHelper = new AwardDBHelper(this);
    EditText name, year, month, day, awardName, agency, content;
    Intent intent;
    int ID;
    ScrollView scrollView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputaward);
        init();
        intent = getIntent();
        ID = Integer.parseInt(intent.getStringExtra("ID"));
        name.setText(intent.getStringExtra("name"));
        year.setText(intent.getStringExtra("year"));
        month.setText(intent.getStringExtra("month"));
        day.setText(intent.getStringExtra("day"));
        awardName.setText(intent.getStringExtra("awardname"));
        agency.setText(intent.getStringExtra("agency"));
        content.setText(intent.getStringExtra("content"));
    }
    public void init()
    {
        name = (EditText) findViewById(R.id.name);
        year = (EditText) findViewById(R.id.year);
        month = (EditText) findViewById(R.id.month);
        day = (EditText) findViewById(R.id.day);
        agency = (EditText) findViewById(R.id.agency);
        awardName = (EditText) findViewById(R.id.awardname);
        content = (EditText) findViewById(R.id.content);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.cancel) {
            setResult(RESULT_CANCELED, intent);
            finish();
        }
        else {
            try{
                String name2 = name.getText().toString();
                String year2 = year.getText().toString();
                String month2 = month.getText().toString();
                String day2 = day.getText().toString();
                String awardname2 = awardName.getText().toString();
                String agency2 = agency.getText().toString();
                String content2 = content.getText().toString();

                if(month.getText().toString().length() == 1)
                    month2 = "0" + month.getText().toString();
                else
                    month2 = month.getText().toString();

                if(day.getText().toString().length() == 1)
                    day2 = "0" + day.getText().toString();
                else
                    day2 = day.getText().toString();

                if(name2.length() > 0 && awardname2.length() > 0 && (year2.length() == 4) && (month2.length() == 2) && (day2.length() == 2) && content2.length() > 0 && agency2.length() > 0)
                {
                    SQLiteDatabase sdb = dbHelper.getWritableDatabase();
                    sdb.execSQL("UPDATE awardDB SET name='"+name2+"', year='"+year2+"', month='"+month2+"',day='"+day2+"',awardname='"+awardname2+"',agency='"+agency2+"',content='"+ content2 +"' WHERE id ="+ID+";");
                    Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    if(name2.length() == 0) {
                        name.requestFocus();
                        Toast.makeText(this, "대회명을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(awardname2.length() == 0) {
                        awardName.requestFocus();
                        Toast.makeText(this, "상장명을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(agency2.length() == 0){
                        agency.requestFocus();
                        Toast.makeText(this, "수상기관을 입력해주세요", Toast.LENGTH_SHORT).show();
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
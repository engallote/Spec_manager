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

public class CertificationAdd extends AppCompatActivity{
    private CertificationDBHelper dbHelper = new CertificationDBHelper(this);
    EditText name, year, month, day, number, agency;
    Intent intent = new Intent();
    ScrollView scrollView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputcer);
        init();
    }
    public void init()
    {
        name = (EditText) findViewById(R.id.name);
        year = (EditText) findViewById(R.id.year);
        month = (EditText) findViewById(R.id.month);
        day = (EditText) findViewById(R.id.day);
        agency = (EditText) findViewById(R.id.agency);
        number = (EditText) findViewById(R.id.number);
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
                String number2 = number.getText().toString();
                String agency2 = agency.getText().toString();

                if(month.getText().toString().length() == 1)
                    month2 = "0" + month.getText().toString();
                else
                    month2 = month.getText().toString();

                if(day.getText().toString().length() == 1)
                    day2 = "0" + day.getText().toString();
                else
                    day2 = day.getText().toString();

                if(name2.length() > 0 && number2.length() > 0 && (year2.length() == 4) && (month2.length() == 2)  && (day2.length() == 2) && agency2.length() > 0)
                {
                    SQLiteDatabase sdb = dbHelper.getWritableDatabase();
                    sdb.execSQL("insert into cerDB(name, year, month, day, number, agency) values('"+name2+"', '"+year2+"', '"+month2+"','"+day2+"', '"+number2+"','"+agency2+"');");
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    if(name2.length() == 0) {
                        name.requestFocus();
                        Toast.makeText(this, "자격증 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(number2.length() == 0)
                    {
                        number.requestFocus();
                        Toast.makeText(this, "자격번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(agency2.length() == 0)
                    {
                        agency.requestFocus();
                        Toast.makeText(this, "발급기관을 입력해주세요", Toast.LENGTH_SHORT).show();
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
                }
            }
            catch (SQLiteException e)
            {
                Toast.makeText(this, "에러!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

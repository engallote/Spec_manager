package moon.spec7;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

public class LanguageAdd extends AppCompatActivity{
    private LanguageDBHelper dbHelper = new LanguageDBHelper(this);
    EditText name, year1, month1, day1, year2, month2, day2, score, number, number2, agency;
    RadioGroup rg;
    RadioButton yes, no;
    String chk = "true";
    Intent intent = new Intent();
    ScrollView scrollView;
    boolean flag = true;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputlan);
        init();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.yes:
                        chk = "true";
                        year2.setEnabled(true);
                        month2.setEnabled(true);
                        day2.setEnabled(true);
                        year2.setHint("YYYY");
                        month2.setHint("MM");
                        day2.setHint("DD");
                        year2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
                        month2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
                        day2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
                        flag = true;
                        break;
                    case R.id.no:
                        chk = "false";
                        year2.setEnabled(false);
                        month2.setEnabled(false);
                        day2.setEnabled(false);
                        year2.setHint("");
                        month2.setHint("");
                        day2.setHint("");
                        year2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
                        month2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
                        day2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
                        flag = false;
                        break;
                }
            }
        });
    }
    public void init()
    {
        name = (EditText) findViewById(R.id.name);
        year1 = (EditText) findViewById(R.id.year1);
        month1 = (EditText) findViewById(R.id.month1);
        day1 = (EditText) findViewById(R.id.day1);
        year2 = (EditText) findViewById(R.id.year2);
        month2 = (EditText) findViewById(R.id.month2);
        day2 = (EditText) findViewById(R.id.day2);
        score = (EditText) findViewById(R.id.score);
        number = (EditText) findViewById(R.id.number);
        number2 = (EditText) findViewById(R.id.number2);
        agency = (EditText) findViewById(R.id.agency);
        rg = (RadioGroup) findViewById(R.id.rg);
        yes = (RadioButton) findViewById(R.id.yes);
        no = (RadioButton) findViewById(R.id.no);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.cancel) {
            setResult(RESULT_CANCELED, intent);
            finish();
        }
        else {
            try{
                String t_name = name.getText().toString();
                String t_year1 = year1.getText().toString();
                String t_month1 = month1.getText().toString();
                String t_day1 = day1.getText().toString();
                String t_year2 = "", t_month2 = "", t_day2 = "";
                String t_score = score.getText().toString();
                String t_number = number.getText().toString();
                String t_number2 = number2.getText().toString();
                String t_agency = agency.getText().toString();

                if(t_number2.length() == 0)
                    t_number2="알수없음";
                if(!flag)
                {
                    t_year2="9999";
                    t_month2="99";
                    t_day2="99";
                }
                else
                {
                    t_year2 = year2.getText().toString();
//                    t_month2 = month2.getText().toString();
//                    t_day2 = day2.getText().toString();
                    if(month2.getText().toString().length() == 1)
                        t_month2 = "0" + month2.getText().toString();
                    else
                        t_month2 = month2.getText().toString();

                    if(day2.getText().toString().length() == 1)
                        t_day2 = "0" + day2.getText().toString();
                    else
                        t_day2 = day2.getText().toString();
                }

                if(t_month1.length() == 1)
                    t_month1 = "0" + month1.getText().toString();
                else
                    t_month1 = month1.getText().toString();

                if(t_day1.length() == 1)
                    t_day1 = "0" + day1.getText().toString();
                else
                    t_day1 = day1.getText().toString();

                if(t_name.length() > 0 && (t_year1.length() == 4) && (t_month1.length() == 2)  && (t_day1.length() == 2) && (t_year2.length() == 4) && (t_month2.length() == 2)  && (t_day2.length() == 2)
                        && t_score.length() > 0 && t_number.length() > 0 && t_agency.length() > 0)
                {
                    SQLiteDatabase sdb = dbHelper.getWritableDatabase();
                    sdb.execSQL("insert into lanDB(name,year1,month1,day1,year2,month2,day2,score,number,number2,agency,chk) values('"+t_name+"', '"+t_year1+"', '"+t_month1+"','"+t_day1+"', '"+t_year2+"', '"
                            + t_month2 +"', '" + t_day2 + "', '" + t_score + "', '" + t_number + "', '" + t_number2 + "', '" + t_agency + "', '" + chk + "');");
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    if(t_name.length() == 0) {
                        name.requestFocus();
                        Toast.makeText(this, "언어 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_number.length() == 0) {
                        number.requestFocus();
                        Toast.makeText(this, "등록번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_agency.length() == 0) {
                        agency.requestFocus();
                        Toast.makeText(this, "발급기관을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_year1.length() < 4) {
                        year1.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_month1.length() == 0) {
                        month1.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_day1.length() == 0) {
                        day1.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(flag && t_year2.length() < 4){
                        year2.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(flag && t_month2.length() == 0) {
                        month2.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(flag && t_day2.length() == 0) {
                        day2.requestFocus();
                        Toast.makeText(this, "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_score.length() == 0) {
                        score.requestFocus();
                        Toast.makeText(this, "점수를 입력해주세요", Toast.LENGTH_SHORT).show();
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

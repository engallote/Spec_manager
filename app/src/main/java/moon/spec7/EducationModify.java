package moon.spec7;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

public class EducationModify extends AppCompatActivity{
    private EducationDBHelper dbHelper = new EducationDBHelper(this);
    EditText name, year1, month1, day1, year2, month2, day2, major, submajor, score, majorscore, grade, majorgrade, etc;
    ScrollView scrollView;
    Spinner area, time, status;
    RadioGroup rg;
    RadioButton main, sub;
    String userArea = "", userTime = "", userStatus = "", school = "";
    int ID;
    String[] date = new String[3];
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputeducation);
        intent = getIntent();
        init();

        ID = Integer.parseInt(intent.getStringExtra("ID"));
        name.setText(intent.getStringExtra("name"));
        year1.setText(intent.getStringExtra("year1"));
        date[0] = intent.getStringExtra("year1");
        month1.setText(intent.getStringExtra("month1"));
        date[1] = intent.getStringExtra("month1");
        day1.setText(intent.getStringExtra("day1"));
        date[2] = intent.getStringExtra("day1");
        year2.setText(intent.getStringExtra("year2"));
        month2.setText(intent.getStringExtra("month2"));
        day2.setText(intent.getStringExtra("day2"));
        major.setText(intent.getStringExtra("major"));
        submajor.setText(intent.getStringExtra("submajor"));
        majorgrade.setText(intent.getStringExtra("majorgrade"));
        grade.setText(intent.getStringExtra("grade"));
        majorscore.setText(intent.getStringExtra("majorscore"));
        score.setText(intent.getStringExtra("score"));
        etc.setText(intent.getStringExtra("etc"));
        userTime = intent.getStringExtra("time");

        if(userTime.equals("주간"))
            time.setSelection(0);
        else
            time.setSelection(1);

        userStatus = intent.getStringExtra("status");
        if(userStatus.equals("없음"))
            status.setSelection(0);
        if(userStatus.equals("전과"))
            status.setSelection(1);
        if(userStatus.equals("편입"))
            status.setSelection(2);
        if(userStatus.equals("자퇴"))
            status.setSelection(3);
        if(userStatus.equals("제적"))
            status.setSelection(4);

        userArea = intent.getStringExtra("area");
        if(userArea.equals("서울"))
            area.setSelection(0);
        else if(userArea.equals("경기"))
            area.setSelection(1);
        else if(userArea.equals("인천"))
            area.setSelection(2);
        else if(userArea.equals("강원"))
            area.setSelection(3);
        else if(userArea.equals("충청"))
            area.setSelection(4);
        else if(userArea.equals("경상"))
            area.setSelection(5);
        else if(userArea.equals("전라"))
            area.setSelection(6);
        else if(userArea.equals("제주"))
            area.setSelection(7);
        else
            area.setSelection(8);

        school = intent.getStringExtra("school");
        if(school.equals("본교"))
            main.setChecked(true);
        else
            sub.setChecked(true);

        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userArea = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userTime = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userStatus = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.main:
                        school = "본교";
                        break;
                    case R.id.sub:
                        school = "분교";
                        break;
                }
            }
        });
    }
    public void init()
    {
        name = (EditText) findViewById(R.id.name);
        major = (EditText) findViewById(R.id.major);
        submajor = (EditText) findViewById(R.id.submajor);
        score = (EditText) findViewById(R.id.score);
        majorscore = (EditText) findViewById(R.id.majorscore);
        grade = (EditText) findViewById(R.id.grade);
        majorgrade = (EditText) findViewById(R.id.majorgrade);
        etc = (EditText) findViewById(R.id.etc);
        year1 = (EditText) findViewById(R.id.year1);
        month1 = (EditText) findViewById(R.id.month1);
        day1 = (EditText) findViewById(R.id.day1);
        year2 = (EditText) findViewById(R.id.year2);
        month2 = (EditText) findViewById(R.id.month2);
        day2 = (EditText) findViewById(R.id.day2);
        area = (Spinner) findViewById(R.id.area);
        time = (Spinner) findViewById(R.id.time);
        status = (Spinner) findViewById(R.id.status);
        rg = (RadioGroup) findViewById(R.id.rg);
        main = (RadioButton) findViewById(R.id.main);
        sub = (RadioButton) findViewById(R.id.sub);
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
                String t_major = major.getText().toString();
                String t_submajor = submajor.getText().toString();
                String t_majorscore = majorscore.getText().toString();
                String t_score = score.getText().toString();
                String t_grade = grade.getText().toString();
                String t_majorgrade = majorgrade.getText().toString();
                String t_etc = etc.getText().toString();
                String t_year1 = year1.getText().toString();
                String t_month1 = month1.getText().toString();
                String t_day1 = day1.getText().toString();
                String t_year2 = year2.getText().toString();
                String t_month2 = month2.getText().toString();
                String t_day2 = day2.getText().toString();

                if(t_submajor.length() == 0)
                    t_submajor = "없음";
                else
                    t_submajor = submajor.getText().toString();

                if(month1.getText().toString().length() == 1)
                    t_month1 = "0" + month1.getText().toString();
                else
                    t_month1 = month1.getText().toString();

                if(day1.getText().toString().length() == 1)
                    t_day1 = "0" + day1.getText().toString();
                else
                    t_day1 = day1.getText().toString();

                if(month2.getText().toString().length() == 1)
                    t_month2 = "0" + month2.getText().toString();
                else
                    t_month2 = month2.getText().toString();

                if(day2.getText().toString().length() == 1)
                    t_day2 = "0" + day2.getText().toString();
                else
                    t_day2 = day2.getText().toString();

                if(t_name.length() > 0 && t_major.length() > 0 && (t_year1.length() == 4) && (t_month1.length() == 2)  && (t_day1.length() == 2) && (t_year2.length() == 4) && (t_month2.length() == 2)
                        && (t_day2.length() == 2) && t_score.length() > 0 && t_majorscore.length() > 0 && t_grade.length() > 0 && t_majorgrade.length() > 0)
                {
                    SQLiteDatabase sdb = dbHelper.getWritableDatabase();
                    sdb.execSQL("UPDATE eduDB SET name='"+t_name+"', time='"+ userTime + "', area='" + userArea + "', school='" + school + "', major='" + t_major + "', submajor='" + t_submajor + "', score='" + t_score + "', majorscore='" + t_majorscore
                            + "', majorgrade='" + t_majorgrade + "', grade='" + t_grade + "', status='" + userStatus + "', year1='" + t_year1 + "', month1='" + t_month1 + "', day1='" + t_day1 + "', year2='" + t_year2 + "', month2='" + t_month2 + "', day2='" + t_day2
                            + "', etc='" + t_etc + "' WHERE id = " + ID + ";");
                    Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    if(t_name.length() == 0) {
                        name.requestFocus();
                        Toast.makeText(this, "학교명을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_major.length() == 0) {
                        major.requestFocus();
                        Toast.makeText(this, "전공을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_majorscore.length() == 0) {
                        majorscore.requestFocus();
                        Toast.makeText(this, "전공평점을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_score.length() == 0) {
                        score.requestFocus();
                        Toast.makeText(this, "총평점을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_majorgrade.length() == 0) {
                        majorgrade.requestFocus();
                        Toast.makeText(this, "전공학점을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_grade.length() == 0) {
                        grade.requestFocus();
                        Toast.makeText(this, "이수학점을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_year1.length() < 4) {
                        year1.requestFocus();
                        Toast.makeText(this, "입학 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_month1.length() == 0) {
                        month1.requestFocus();
                        Toast.makeText(this, "입학 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_day1.length() == 0) {
                        day1.requestFocus();
                        Toast.makeText(this, "입학 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_year2.length() < 4) {
                        year2.requestFocus();
                        Toast.makeText(this, "졸업(예정) 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_month2.length() == 0) {
                        month2.requestFocus();
                        Toast.makeText(this, "졸업(예정) 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_day2.length() == 0) {
                        day2.requestFocus();
                        Toast.makeText(this, "졸업(예정) 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
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

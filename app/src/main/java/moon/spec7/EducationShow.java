package moon.spec7;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class EducationShow extends AppCompatActivity{
    TextView name, date1, date2, major, submajor, score, majorscore, grade, majorgrade, etc, area, time, status, school;
    String year1, month1,day1,year2,month2,day2,ID;
    ScrollView scrollView;
    Intent intent;
    final int choose_code = 500;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showeducation);
        intent = getIntent();
        init();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ID = intent.getStringExtra("ID");
        name.setText(intent.getStringExtra("name"));
        time.setText(intent.getStringExtra("time"));
        area.setText(intent.getStringExtra("area"));
        school.setText(intent.getStringExtra("school"));
        status.setText(intent.getStringExtra("status"));
        major.setText(intent.getStringExtra("major"));
        submajor.setText(intent.getStringExtra("submajor"));
        majorscore.setText(intent.getStringExtra("majorscore"));
        score.setText(intent.getStringExtra("score"));
        majorgrade.setText(intent.getStringExtra("majorgrade"));
        grade.setText(intent.getStringExtra("grade"));
        etc.setText(intent.getStringExtra("etc"));
        year1 = intent.getStringExtra("year1");
        month1 = intent.getStringExtra("month1");
        day1 = intent.getStringExtra("day1");
        String t_date1 = year1 + "년 " + month1 + "월 " + day1 + "일";
        year2 = intent.getStringExtra("year2");
        month2 = intent.getStringExtra("month2");
        day2 = intent.getStringExtra("day2");
        String t_date2 = year2 + "년 " + month2 + "월 " + day2 + "일";
        date1.setText(t_date1);
        date2.setText(t_date2);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    public void init()
    {
        name = (TextView) findViewById(R.id.name);
        major = (TextView) findViewById(R.id.major);
        submajor = (TextView) findViewById(R.id.submajor);
        score = (TextView) findViewById(R.id.score);
        majorscore = (TextView) findViewById(R.id.majorscore);
        grade = (TextView) findViewById(R.id.grade);
        majorgrade = (TextView) findViewById(R.id.majorgrade);
        etc = (TextView) findViewById(R.id.etc);
        date1 = (TextView) findViewById(R.id.date1);
        date2 = (TextView) findViewById(R.id.date2);
        area = (TextView) findViewById(R.id.area);
        time = (TextView) findViewById(R.id.time);
        school = (TextView) findViewById(R.id.school);
        status = (TextView) findViewById(R.id.status);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }
    public void onClick(View v) {
        try{
            Intent intent2 = new Intent(EducationShow.this, EducationModify.class);
            intent2.putExtra("ID", ID);
            intent2.putExtra("name", name.getText().toString());
            intent2.putExtra("year1", year1);
            intent2.putExtra("month1", month1);
            intent2.putExtra("day1", day1);
            intent2.putExtra("year2", year2);
            intent2.putExtra("month2", month2);
            intent2.putExtra("day2", day2);
            intent2.putExtra("major", major.getText().toString());
            intent2.putExtra("submajor", submajor.getText().toString());
            intent2.putExtra("majorgrade", majorgrade.getText().toString());
            intent2.putExtra("grade", grade.getText().toString());
            intent2.putExtra("majorscore", majorscore.getText().toString());
            intent2.putExtra("score", score.getText().toString());
            intent2.putExtra("area", area.getText().toString());
            intent2.putExtra("status", status.getText().toString());
            intent2.putExtra("time", time.getText().toString());
            intent2.putExtra("school", school.getText().toString());
            intent2.putExtra("etc", etc.getText().toString());
            startActivityForResult(intent2, choose_code);
        }
        catch (SQLiteException e)
        {
            Toast.makeText(this, "에러!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == choose_code)
        {
            if(resultCode == RESULT_OK)
                setResult(RESULT_OK, intent);
            else
                setResult(RESULT_CANCELED, intent);
        }
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}

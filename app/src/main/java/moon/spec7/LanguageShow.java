package moon.spec7;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LanguageShow extends AppCompatActivity{
    TextView name, date1, date2, number, number2, score, agency;
    final int choose_code = 200;
    String year1,month1,day1,year2,month2,day2,chk,ID;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlan);
        intent = getIntent();
        init();
        ID = intent.getStringExtra("ID");
        name.setText(intent.getStringExtra("name"));
        year1 = intent.getStringExtra("year1");
        month1 = intent.getStringExtra("month1");
        day1 = intent.getStringExtra("day1");
        year2 = intent.getStringExtra("year2");
        month2 = intent.getStringExtra("month2");
        day2 = intent.getStringExtra("day2");

        String t_date1 = year1 + "년 " + month1 + "월 " + day1 + "일";
        String t_date2 = year2 + "년 " + month2 + "월 " + day2 + "일";
//        if(intent.getStringExtra("year2").equals("9999"))
//            t_date2="유효기간 없음";
        chk = intent.getStringExtra("chk");
        if(chk.equals("false"))
            t_date2="유효기간 없음";
        date1.setText(t_date1);
        date2.setText(t_date2);
        number.setText(intent.getStringExtra("number"));
        number2.setText(intent.getStringExtra("number2"));
        score.setText(intent.getStringExtra("score"));
        agency.setText(intent.getStringExtra("agency"));
    }
    public void init(){
        name = (TextView) findViewById(R.id.name);
        date1 = (TextView) findViewById(R.id.date1);
        date2 = (TextView) findViewById(R.id.date2);
        number = (TextView) findViewById(R.id.number);
        number2 = (TextView) findViewById(R.id.number2);
        score = (TextView) findViewById(R.id.score);
        agency = (TextView) findViewById(R.id.agency);
    }
    public void onClick(View v) {
        try{
            Intent intent2 = new Intent(LanguageShow.this, LanguageModify.class);
            intent2.putExtra("ID", ID);
            intent2.putExtra("name", name.getText().toString());
            intent2.putExtra("year1", year1);
            intent2.putExtra("month1", month1);
            intent2.putExtra("day1", day1);
            intent2.putExtra("year2", year2);
            intent2.putExtra("month2", month2);
            intent2.putExtra("day2", day2);
            intent2.putExtra("number", number.getText().toString());
            intent2.putExtra("number2", number2.getText().toString());
            intent2.putExtra("score", score.getText().toString());
            intent2.putExtra("agency", agency.getText().toString());
            intent2.putExtra("chk", chk);
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
            {
                setResult(RESULT_OK, intent);
            }
            else
                setResult(RESULT_CANCELED, intent);
        }
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}

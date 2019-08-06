package moon.spec7;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AwardShow extends AppCompatActivity{
    TextView name, date, awardname, agency, content;
    String year, month, day, ID;
    ScrollView scrollView;
    Intent intent;
    final int choose_code = 300;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showaward);
        intent = getIntent();
        init();
        ID = intent.getStringExtra("ID");
        name.setText(intent.getStringExtra("name"));
        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        day = intent.getStringExtra("day");
        String date1 =  year + "년 " + month + "월 " + day + "일";
        date.setText(date1);
        awardname.setText(intent.getStringExtra("awardname"));
        agency.setText(intent.getStringExtra("agency"));
        content.setText(intent.getStringExtra("content"));
    }
    public void init(){
        name = (TextView) findViewById(R.id.name);
        date = (TextView) findViewById(R.id.date);
        awardname = (TextView) findViewById(R.id.awardname);
        agency = (TextView) findViewById(R.id.agency);
        content = (TextView) findViewById(R.id.content);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
    }
    public void onClick(View v) {
        try{
            Intent intent2 = new Intent(AwardShow.this, AwardModify.class);
            intent2.putExtra("ID", ID);
            intent2.putExtra("name", name.getText().toString());
            intent2.putExtra("year", year);
            intent2.putExtra("month", month);
            intent2.putExtra("day", day);
            intent2.putExtra("awardname", awardname.getText().toString());
            intent2.putExtra("agency", agency.getText().toString());
            intent2.putExtra("content", content.getText().toString());
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

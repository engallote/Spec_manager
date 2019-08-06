package moon.spec7;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CareerShow extends AppCompatActivity{
    TextView name, date1, date2, form, task, etc, adress, pos1, pos2;
    Intent intent;
    String year1, month1, day1, year2, month2, day2, status, ID;
    final int choose_code = 400;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcareer);
        intent = getIntent();
        init();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ID = intent.getStringExtra("ID");
        name.setText(intent.getStringExtra("name"));
        adress.setText(intent.getStringExtra("adress"));
        pos1.setText(intent.getStringExtra("position1"));
        pos2.setText(intent.getStringExtra("position2"));
        status = intent.getStringExtra("status");
        etc.setText(intent.getStringExtra("etc"));
        form.setText(intent.getStringExtra("form"));
        task.setText(intent.getStringExtra("task"));
        year1 = intent.getStringExtra("year1");
        month1 = intent.getStringExtra("month1");
        day1 = intent.getStringExtra("day1");
        String t_date1 = year1 + "년 " + month1 + "월 " + day1 + "일";
        date1.setText(t_date1);

        year2 = intent.getStringExtra("year2");
        month2 = intent.getStringExtra("month2");
        day2 = intent.getStringExtra("day2");
        if(status.equals("재직"))
        {
            date2.setText("재직중");
        }
        else
        {
            String t_date2 =  year2 + "년 " + month2 + "월 " + day2 + "일";
            date2.setText(t_date2);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    public void init()
    {
        name = (TextView) findViewById(R.id.name);
        form = (TextView) findViewById(R.id.form);
        task = (TextView) findViewById(R.id.task);
        etc = (TextView) findViewById(R.id.etc);
        date1 = (TextView) findViewById(R.id.date1);
        date2 = (TextView) findViewById(R.id.date2);
        adress = (TextView) findViewById(R.id.adress);
        pos1 = (TextView) findViewById(R.id.position1);
        pos2 = (TextView) findViewById(R.id.position2);
    }
    public void onClick(View v) {
        try{
            Intent intent2 = new Intent(CareerShow.this, CareerModify.class);
            intent2.putExtra("ID", ID);
            intent2.putExtra("name", name.getText().toString());
            intent2.putExtra("adress", adress.getText().toString());
            intent2.putExtra("position1", pos1.getText().toString());
            intent2.putExtra("position2", pos2.getText().toString());
            intent2.putExtra("year1", year1);
            intent2.putExtra("month1", month1);
            intent2.putExtra("day1", day1);
            intent2.putExtra("year2", year2);
            intent2.putExtra("month2", month2);
            intent2.putExtra("day2", day2);
            intent2.putExtra("status", status);
            intent2.putExtra("form", form.getText().toString());
            intent2.putExtra("task", task.getText().toString());
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
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

public class CareerModify extends AppCompatActivity{
    private CareerDBHelper dbHelper = new CareerDBHelper(this);
    ScrollView scrollView;
    EditText name, year1, month1, day1, year2, month2, day2, task, etc, pos1, pos2, adress;
    Spinner form;
    RadioGroup rg;
    RadioButton work, out;
    String userForm = "", userStatus = "";
    int ID;
    String[] oriDate = new String[3];
    boolean flag = true;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputcareer);
        init();
        intent = getIntent();

        ID = Integer.parseInt(intent.getStringExtra("ID"));
        name.setText(intent.getStringExtra("name"));
        if(intent.getStringExtra("adress").equals(" "))
            adress.setText("");
        else
            adress.setText(intent.getStringExtra("adress"));
        pos1.setText(intent.getStringExtra("position1"));
        pos2.setText(intent.getStringExtra("position2"));

        year1.setText(intent.getStringExtra("year1"));
        month1.setText(intent.getStringExtra("month1"));
        day1.setText(intent.getStringExtra("day1"));

        oriDate[0] = intent.getStringExtra("year1");
        oriDate[1] = intent.getStringExtra("month1");
        oriDate[2] = intent.getStringExtra("day1");

        year2.setText(intent.getStringExtra("year2"));
        month2.setText(intent.getStringExtra("month2"));
        day2.setText(intent.getStringExtra("day2"));
        task.setText(intent.getStringExtra("task"));
        etc.setText(intent.getStringExtra("etc"));

        userForm = intent.getStringExtra("form");
        if(userForm.equals("정규직"))
            form.setSelection(0);
        else if(userForm.equals("계약직"))
            form.setSelection(1);
        else
            form.setSelection(2);

        userStatus = intent.getStringExtra("status");
        if(userStatus.equals("재직")) {
            year2.setText("");
            month2.setText("");
            flag = true;
            day2.setText("");
            year2.setHint("");
            month2.setHint("");
            day2.setHint("");
            work.setChecked(true);
            year2.setEnabled(false);
            month2.setEnabled(false);
            day2.setEnabled(false);
            year2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
            month2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
            day2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
        }
        else {
            out.setChecked(true);
            flag = false;
            year2.setText(intent.getStringExtra("year2"));
            month2.setText(intent.getStringExtra("month2"));
            day2.setText(intent.getStringExtra("day2"));
            year2.setEnabled(true);
            month2.setEnabled(true);
            day2.setEnabled(true);
            year2.setHint("YYYY");
            month2.setHint("MM");
            day2.setHint("DD");
            year2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
            month2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
            day2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.work:
                        userStatus = "재직";
                        year2.setEnabled(false);
                        month2.setEnabled(false);
                        day2.setEnabled(false);
                        year2.setHint("");
                        month2.setHint("");
                        day2.setHint("");
                        year2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
                        month2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
                        day2.setBackground(getResources().getDrawable(R.drawable.false_shape_attr));
                        flag = true;
                        break;
                    case R.id.out:
                        userStatus = "퇴사";
                        year2.setEnabled(true);
                        month2.setEnabled(true);
                        day2.setEnabled(true);
                        year2.setHint("YYYY");
                        month2.setHint("MM");
                        day2.setHint("DD");
                        year2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
                        month2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
                        day2.setBackground(getResources().getDrawable(R.drawable.edittext_shape_attr));
                        flag = false;
                        break;
                }
            }
        });
        form.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userForm = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void init()
    {
        name = (EditText) findViewById(R.id.name);
        adress = (EditText) findViewById(R.id.adress);
        pos1= (EditText) findViewById(R.id.position1);
        pos2 = (EditText) findViewById(R.id.position2);
        etc = (EditText) findViewById(R.id.etc);
        year1 = (EditText) findViewById(R.id.year1);
        month1 = (EditText) findViewById(R.id.month1);
        day1 = (EditText) findViewById(R.id.day1);
        year2 = (EditText) findViewById(R.id.year2);
        month2 = (EditText) findViewById(R.id.month2);
        day2 = (EditText) findViewById(R.id.day2);
        task = (EditText) findViewById(R.id.task);
        form = (Spinner) findViewById(R.id.form);
        rg = (RadioGroup) findViewById(R.id.rg);
        work = (RadioButton) findViewById(R.id.work);
        out = (RadioButton) findViewById(R.id.out);
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
                String t_task = task.getText().toString();
                String t_adress = adress.getText().toString();
                String t_pos1 = pos1.getText().toString();
                String t_pos2 = pos2.getText().toString();
                String t_etc = etc.getText().toString();
                String t_year1 = year1.getText().toString();
                String t_month1 = month1.getText().toString();
                String t_day1 = day1.getText().toString();
                String t_year2 = "",t_month2 = "",t_day2="";

                if(t_adress.length() == 0)
                    t_adress = " ";
                else
                    t_adress = adress.getText().toString();

                if(t_pos2.length() == 0)
                    t_pos2 = "없음";
                else
                    t_pos2 = pos2.getText().toString();

                if(flag){
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

                if(t_name.length() > 0 && t_task.length() > 0 && t_pos1.length() > 0 && (t_year1.length() == 4) && (t_month1.length() == 2)  && (t_day1.length() == 2)
                        && (t_year2.length() == 4) && (t_month2.length() == 2)  && (t_day2.length() == 2))
                {
                    SQLiteDatabase sdb = dbHelper.getWritableDatabase();
                    sdb.execSQL("UPDATE careerDB SET name='"+t_name+"', adress='" + t_adress + "', status='"+ userStatus + "', form='" + userForm + "', position1='" + t_pos1 + "', position2='" + t_pos2+"', task='" + t_task + "', year1='" + t_year1 + "', month1='" + t_month1 + "', day1='" + t_day1
                            + "', year2='" + t_year2 + "', month2='" + t_month2 + "', day2='" + t_day2 + "', etc='" + t_etc + "' WHERE id = " + ID +";");
                    Toast.makeText(this, "수정 완료", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    if(t_name.length() == 0) {
                        name.requestFocus();
                        Toast.makeText(this, "회사명을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_pos1.length() == 0) {
                        pos1.requestFocus();
                        Toast.makeText(this, "직위를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_task.length() == 0) {
                        task.requestFocus();
                        Toast.makeText(this, "업무를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_year1.length() < 4) {
                        year1.requestFocus();
                        Toast.makeText(this, "입사 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_month1.length() == 0) {
                        month1.requestFocus();
                        Toast.makeText(this, "입사 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_day1.length() == 0) {
                        day1.requestFocus();
                        Toast.makeText(this, "입사 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_year2.length() < 4) {
                        year2.requestFocus();
                        Toast.makeText(this, "퇴사 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_month2.length() == 0) {
                        month2.requestFocus();
                        Toast.makeText(this, "퇴사 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(t_day2.length() == 0) {
                        day2.requestFocus();
                        Toast.makeText(this, "퇴사 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
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

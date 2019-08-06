package moon.spec7;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class CareerList extends AppCompatActivity {
    private Spinner spinner;
    private FloatingActionButton fab;
    private String page = "경력관리";
    ListView listView;
    private ArrayList<Integer> idarr = new ArrayList<>();
    private ArrayList<CareerListItem> item = new ArrayList<>();
    private CareerDBHelper dbHelper = new CareerDBHelper(this);
    private Cursor cursor;
    private CareerListAdapter adapter;
    final int choose_code = 7;
    boolean lastitemVisibleFlag = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listcareer);
        setListView();
        loadItem();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareerList.this, CareerAdd.class);
                startActivityForResult(intent, choose_code);
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner_menu);
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                page = parent.getItemAtPosition(position).toString();
                Intent intent;
                if(page.equals("학력관리"))
                {
                    intent = new Intent(CareerList.this, EducationList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("수상관리"))
                {
                    intent = new Intent(CareerList.this, AwardList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("경력관리"))
                {
//                    intent = new Intent(CareerList.this, CareerList.class);
//                    startActivity(intent);
//                    finish();
                }
                else if(page.equals("경험관리"))
                {
                    intent = new Intent(CareerList.this, ExpList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("어학관리"))
                {
                    intent = new Intent(CareerList.this, LanguageList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("자격증관리"))
                {
                    intent = new Intent(CareerList.this, CertificationList.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    public void setListView() {
        listView = (ListView) findViewById(R.id.list);
        adapter = new CareerListAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CareerList.this, CareerShow.class);
                String name=item.get(position).getName(), form=item.get(position).getForm(), task=item.get(position).getTask(), status=item.get(position).getStatus(), etc=item.get(position).getEtc()
                        , position1 = item.get(position).getPosition1(), position2 = item.get(position).getPosition2(), adress = item.get(position).getAdress();
                String ID = Long.toString(idarr.get(position));
                String year1="", month1 ="", day1="", year2 ="", month2="",day2="";
                String[] date1 = item.get(position).getDate1().split("/"), date2 = item.get(position).getDate2().split("/");
                year1 = date1[0];
                month1 = date1[1];
                day1 = date1[2];
                year2 = date2[0];
                month2 = date2[1];
                day2 = date2[2];

                intent.putExtra("ID", ID);
                intent.putExtra("name", name);
                intent.putExtra("form", form);
                intent.putExtra("task", task);
                intent.putExtra("position1", position1);
                intent.putExtra("position2", position2);
                intent.putExtra("adress", adress);
                intent.putExtra("year1", year1);
                intent.putExtra("month1", month1);
                intent.putExtra("day1", day1);
                intent.putExtra("year2", year2);
                intent.putExtra("month2", month2);
                intent.putExtra("day2", day2);
                intent.putExtra("status", status);
                intent.putExtra("etc", etc);
                startActivityForResult(intent, choose_code);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//길게 누르면 삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(CareerList.this);
                final int pos = position;
                dlg.setTitle("삭제확인")
                        .setMessage("삭제하시겠습니까?")
                        .setIcon(R.drawable.deletetitleicon)//타이틀 옆에 뜨는 아이콘
                        .setPositiveButton("닫기", null)
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.removeData(idarr.get(position));
                                item.remove(position);
                                idarr.remove(position);
                                adapter.deleteItem(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
                    // 데이터 로드
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastitemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
            }
        });
    }
    public void loadItem()
    {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "SELECT * from careerDB order by year1, month1, day1 asc;";
        cursor = sdb.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String ID = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String status = cursor.getString(cursor.getColumnIndex("status"));
            String task = cursor.getString(cursor.getColumnIndex("task"));
            String form = cursor.getString(cursor.getColumnIndex("form"));
            String year1 = cursor.getString(cursor.getColumnIndex("year1"));
            String month1 = cursor.getString(cursor.getColumnIndex("month1"));
            String day1 = cursor.getString(cursor.getColumnIndex("day1"));
            String year2 = cursor.getString(cursor.getColumnIndex("year2"));
            String month2 = cursor.getString(cursor.getColumnIndex("month2"));
            String day2 = cursor.getString(cursor.getColumnIndex("day2"));
            String etc = cursor.getString(cursor.getColumnIndex("etc"));
            String position1 = cursor.getString(cursor.getColumnIndex("position1"));
            String position2 = cursor.getString(cursor.getColumnIndex("position2"));
            String adress = cursor.getString(cursor.getColumnIndex("adress"));
            String date1 = year1+"/"+month1+"/"+day1;
            String date2 = year2 + "/" + month2 + "/" + day2;

            CareerListItem careerListItem = new CareerListItem();
            careerListItem.setDate1(date1);
            careerListItem.setDate2(date2);
            careerListItem.setName(name);
            careerListItem.setStatus(status);
            careerListItem.setForm(form);
            careerListItem.setTask(task);
            careerListItem.setEtc(etc);
            careerListItem.setAdress(adress);
            careerListItem.setPosition2(position2);
            careerListItem.setPosition1(position1);
            item.add(careerListItem);
            if(status.equals("재직"))
                date2 = "재직중";
            idarr.add(Integer.parseInt(ID));
            adapter.addItem(name, adress, status, form, position1, position2, task, date1, date2, etc);
        }
        adapter.notifyDataSetChanged();
        cursor.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == choose_code)
        {
            if(resultCode == RESULT_OK)
            {
                item.clear();
                idarr.clear();
                adapter.deleteAllItem();
                loadItem();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

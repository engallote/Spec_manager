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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class LanguageList extends AppCompatActivity {
    private Spinner spinner;
    private Button button;
    private String page = "어학관리";
    private FloatingActionButton fab;
    ListView listView;
    private ArrayList<Integer> idarr = new ArrayList<>();
    private ArrayList<LanguageListItem> item = new ArrayList<>();
    private LanguageDBHelper dbHelper = new LanguageDBHelper(this);
    private Cursor cursor;
    private LanguageListAdapter adapter;
    final int choose_code = 4;
    boolean lastitemVisibleFlag = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listlan);
        setListView();
        loadItem();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanguageList.this, LanguageAdd.class);
                startActivityForResult(intent, choose_code);
            }
        });
//        button = (Button) findViewById(R.id.move);
        spinner = (Spinner) findViewById(R.id.spinner_menu);
        spinner.setSelection(3);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                page = parent.getItemAtPosition(position).toString();
                if(page.equals("학력관리"))
                {
                    intent = new Intent(LanguageList.this, EducationList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("수상관리"))
                {
                    intent = new Intent(LanguageList.this, AwardList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("경력관리"))
                {
                    intent = new Intent(LanguageList.this, CareerList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("경험관리"))
                {
                    intent = new Intent(LanguageList.this, ExpList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("어학관리"))
                {
//                    intent = new Intent(LanguageList.this, LanguageList.class);
//                    startActivity(intent);
                }
                else if(page.equals("자격증관리"))
                {
                    intent = new Intent(LanguageList.this, CertificationList.class);
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
        adapter = new LanguageListAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LanguageList.this, LanguageShow.class);
                String name = item.get(position).getName(), number=item.get(position).getNumber(), number2 = item.get(position).getNumber2(), agency = item.get(position).getAgency(), score = item.get(position).getScore();
                String year1="", month1="",day1="", year2 = "", month2="", day2="", chk = item.get(position).getChk(), ID = Integer.toString(idarr.get(position));
                String[] date1 = item.get(position).getDate1().split("/"), date2 = item.get(position).getDate2().split("/");
                year1 = date1[0];
                month1 = date1[1];
                day1 = date1[2];
                year2 = date2[0];
                month2 = date2[1];
                day2 = date2[2];
                intent.putExtra("ID", ID);
                intent.putExtra("name", name);
                intent.putExtra("year1", year1);
                intent.putExtra("month1", month1);
                intent.putExtra("day1", day1);
                intent.putExtra("year2", year2);
                intent.putExtra("month2", month2);
                intent.putExtra("day2", day2);
                intent.putExtra("number", number);
                intent.putExtra("number2", number2);
                intent.putExtra("score", score);
                intent.putExtra("agency", agency);
                intent.putExtra("chk", chk);
                startActivityForResult(intent, choose_code);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//길게 누르면 삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(LanguageList.this);
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
        String sql = "select * from lanDB order by year1,month1,day1 asc;";
        cursor = sdb.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String year1 = cursor.getString(cursor.getColumnIndex("year1"));
            String month1 = cursor.getString(cursor.getColumnIndex("month1"));
            String day1 = cursor.getString(cursor.getColumnIndex("day1"));
            String year2 = cursor.getString(cursor.getColumnIndex("year2"));
            String month2 = cursor.getString(cursor.getColumnIndex("month2"));
            String day2 = cursor.getString(cursor.getColumnIndex("day2"));
            String score = cursor.getString(cursor.getColumnIndex("score"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String number2 = cursor.getString(cursor.getColumnIndex("number2"));
            String agency = cursor.getString(cursor.getColumnIndex("agency"));
            String chk = cursor.getString(cursor.getColumnIndex("chk"));
            String date1 = year1+"/"+month1+"/"+day1;
            String date2 = year2+"/"+month2+"/"+day2;
            LanguageListItem languageListItem = new LanguageListItem();
            languageListItem.setName(name);
            languageListItem.setDate1(date1);
            languageListItem.setDate2(date2);
            languageListItem.setScore(score);
            languageListItem.setNumber(number);
            languageListItem.setNumber2(number2);
            languageListItem.setAgency(agency);
            languageListItem.setChk(chk);
            item.add(languageListItem);
            idarr.add(Integer.parseInt(id));
            if(chk.equals("false"))
                date2 = "";
            adapter.addItem(name, date1, date2, score, number, number2, agency, chk);
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
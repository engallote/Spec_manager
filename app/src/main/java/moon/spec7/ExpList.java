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

public class ExpList extends AppCompatActivity {
    private Spinner spinner;
    private String page = "경험관리";
    private FloatingActionButton fab;
    ListView listView;
    private ArrayList<Integer> idarr = new ArrayList<>();
    private ArrayList<ExpListItem> item = new ArrayList<>();
    private ExpDBHelper dbHelper = new ExpDBHelper(this);
    private Cursor cursor;
    private ExpListAdapter adapter;
    final int choose_code = 3;
    boolean lastitemVisibleFlag = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listexp);
        setListView();
        loadItem();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpList.this, ExpAdd.class);
                startActivityForResult(intent, choose_code);
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner_menu);
        spinner.setSelection(6);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                page = parent.getItemAtPosition(position).toString();
                Intent intent;
                if(page.equals("학력관리"))
                {
                    intent = new Intent(ExpList.this, EducationList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("수상관리"))
                {
                    intent = new Intent(ExpList.this, AwardList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("경력관리"))
                {
                    intent = new Intent(ExpList.this, CareerList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("경험관리"))
                {
//                    intent = new Intent(ExpList.this, ExpList.class);
//                    startActivity(intent);
//                    finish();
                }
                else if(page.equals("어학관리"))
                {
                    intent = new Intent(ExpList.this, LanguageList.class);
                    startActivity(intent);
                    finish();
                }
                else if(page.equals("자격증관리"))
                {
                    intent = new Intent(ExpList.this, CertificationList.class);
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
        adapter = new ExpListAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExpList.this, ExpShow.class);
                String title = item.get(position).getTitle(), year="", month="",day="",content=item.get(position).getContent(), ID = Integer.toString(idarr.get(position));
                String[] date = item.get(position).getDate().split("/");
                year = date[0];
                month = date[1];
                day = date[2];
                intent.putExtra("ID", ID);
                intent.putExtra("title", title);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("content", content);
                startActivityForResult(intent, choose_code);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//길게 누르면 삭제
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(ExpList.this);
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
//        cursor = sdb.query("expDB", null, null, null, null, null, null);
        String sql = "select * from expDB order by year,month,day asc;";
        cursor = sdb.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String year = cursor.getString(cursor.getColumnIndex("year"));
            String month = cursor.getString(cursor.getColumnIndex("month"));
            String day = cursor.getString(cursor.getColumnIndex("day"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String date = year+"/"+month+"/"+day;
            ExpListItem expListItem = new ExpListItem();
            expListItem.setContent(content);
            expListItem.setTitle(title);
            expListItem.setDate(date);
            item.add(expListItem);
            idarr.add(Integer.parseInt(id));
            adapter.addItem(title.length() >= 30 ? title.substring(0, 30)+"..." : title, date, content.length() >= 20 ? content.substring(0, 20)+"..." : content);
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
                adapter.deleteAllItem();
                item.clear();
                idarr.clear();
                loadItem();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

package moon.spec7;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;

public class MainActivity extends AppCompatActivity {
    private final int time = 2000;
    private long pressedTime = 0;
    private Spinner spinner;
    private String page = "페이지 선택";
    private InterstitialAd interstitialAd;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");//테스트 ID
//        MobileAds.initialize(this, "ca-app-pub-2071297253591003~8001542474");
        //////////광고 가져오기////////////
        interstitialAd = new InterstitialAd(this);
//        interstitialAd.setAdUnitId("ca-app-pub-2071297253591003/6939742246");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");//광고 테스트
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("FE825B7C0231B4844A80EEE8B1B2365E").build();//내 기기에 테스트
        interstitialAd.loadAd(adRequest);
        /////////////////////////

        spinner = (Spinner) findViewById(R.id.spinner_menu);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                page = parent.getItemAtPosition(position).toString();
                if(page.equals("학력관리"))
                {
                    intent = new Intent(MainActivity.this, EducationList.class);
                    startActivity(intent);
                    if(interstitialAd.isLoaded()) {
                        Log.d("TAG", "광고 로드 성공!");
                        interstitialAd.show();
                    }
                    else
                        Log.d("TAG", "광고 로드 실패!");
                }
                else if(page.equals("수상관리"))
                {
                    intent = new Intent(MainActivity.this, AwardList.class);
                    startActivity(intent);
                    if(interstitialAd.isLoaded()) {
                        Log.d("TAG", "광고 로드 성공!");
                        interstitialAd.show();
                    }
                    else
                        Log.d("TAG", "광고 로드 실패!");
                }
                else if(page.equals("경력관리"))
                {
                    intent = new Intent(MainActivity.this, CareerList.class);
                    startActivity(intent);
                    if(interstitialAd.isLoaded()) {
                        Log.d("TAG", "광고 로드 성공!");
                        interstitialAd.show();
                    }
                    else
                        Log.d("TAG", "광고 로드 실패!");
                }
                else if(page.equals("경험관리"))
                {
                    intent = new Intent(MainActivity.this, ExpList.class);
                    startActivity(intent);
                    if(interstitialAd.isLoaded()) {
                        Log.d("TAG", "광고 로드 성공!");
                        interstitialAd.show();
                    }
                    else
                        Log.d("TAG", "광고 로드 실패!");
                }
                else if(page.equals("어학관리"))
                {
                    intent = new Intent(MainActivity.this, LanguageList.class);
                    startActivity(intent);
                    if(interstitialAd.isLoaded()) {
                        Log.d("TAG", "광고 로드 성공!");
                        interstitialAd.show();
                    }
                    else
                        Log.d("TAG", "광고 로드 실패!");
                }
                else if(page.equals("자격증관리"))
                {
                    intent = new Intent(MainActivity.this, CertificationList.class);
                    startActivity(intent);
                    if(interstitialAd.isLoaded()) {
                        Log.d("TAG", "광고 로드 성공!");
                        interstitialAd.show();
                    }
                    else
                        Log.d("TAG", "광고 로드 실패!");
                }
                else
                    page = "페이지 선택";
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void onClick(View v) {
        if(v.getId() == R.id.moveAward)
        {
            intent = new Intent(this, AwardList.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.moveCer)
        {
            intent = new Intent(this, CertificationList.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.moveExp)
        {
            intent = new Intent(this, ExpList.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.moveLan)
        {
            intent = new Intent(this, LanguageList.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.moveEdu)
        {
            intent = new Intent(this, EducationList.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.moveWork)
        {
            intent = new Intent(this, CareerList.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){
        long tmp = System.currentTimeMillis();
        long intervalTime = tmp - pressedTime;

        if(0 <= intervalTime && time >= intervalTime)
            finish();
        else
        {
            pressedTime = tmp;
            Toast.makeText(getApplicationContext(), "뒤로가기를 한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }
}

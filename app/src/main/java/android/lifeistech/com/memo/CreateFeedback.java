package android.lifeistech.com.memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;
import android.widget.SeekBar;

public class CreateFeedback extends AppCompatActivity{

    public Realm realm;
    public EditText commentEditText;
    public TextView satisfaction;
    public SeekBar seekBar;
    public String percent;
    public String kari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_create);

        //realmを開く
        realm = Realm.getDefaultInstance();

        //関連付け
        commentEditText = (EditText)findViewById(R.id.comment);
        satisfaction = (TextView) findViewById(R.id.text_view);
        seekBar = (SeekBar)findViewById(R.id.seekbar);

        Intent intent = getIntent();
        kari = intent.getStringExtra("title");

//        showData();


        // 初期値
        seekBar.setProgress(0);
        // 最大値
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //ツマミがドラッグされると呼ばれる
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {
                        // 68 % のようにフォーマト、
                        // この場合、Locale.USが汎用的に推奨される
                        percent = String.format(Locale.US, "満足度%d %%",progress);
                        satisfaction.setText(percent);

                    }

                    //ツマミがタッチされた時に呼ばれる
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    //ツマミがリリースされた時に呼ばれる
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                });
    }

//    public void showData(){
//        final Project detail = realm.where(Project.class).equalTo("updateDate",
//                getIntent().getStringExtra("updateDate")).findFirst();
//
//
//        commentEditText.setText(detail.comment);
//    }

    public void save2(final String title, final String comment, final String satisfaction, final String logdate){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                Project detail = realm.createObject(Project.class);
                detail.title = title;
                detail.comment = comment;
                detail.satisfaction = satisfaction;
                detail.logdate = logdate;
            }
        });
    }


    public void create(View v){

//        Intent intent = getIntent();

        String title = kari;
        String comment = commentEditText.getText().toString();
        String satisfaction = percent;

        Date today = new Date();
        SimpleDateFormat day = new SimpleDateFormat("MM-dd", Locale.JAPANESE);
        String logdate = day.format(today);

        save2(title, comment, satisfaction, logdate);


        finish();
    }


    }



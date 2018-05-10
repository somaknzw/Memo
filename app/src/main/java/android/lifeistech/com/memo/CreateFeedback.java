package android.lifeistech.com.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
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
    public float manzoku;
    public RatingBar ratingBar;
    public String kari;
    public SeekBar seekBar;
    public int tassei;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_create);

        //realmを開く
        realm = Realm.getDefaultInstance();

        //関連付け
        commentEditText = (EditText)findViewById(R.id.comment);
//        satisfaction = (TextView) findViewById(R.id.text_view);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        ratingBar.setNumStars(5);

        Intent intent = getIntent();
        kari = intent.getStringExtra("title");

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                manzoku = ratingBar.getRating();


            }
        });


        seekBar = (SeekBar) findViewById(R.id.seekBar);
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
                        tassei = seekBar.getProgress();


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



    public void save2(final String title, final String comment, final float satisfaction, final String logdate, final int achievement){

//        startdialog();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                Project detail = realm.createObject(Project.class);
                detail.title = title;
                detail.comment = comment;
                detail.satisfaction = satisfaction;
                detail.logdate = logdate;
                detail.achievement = achievement;

            }
        });
    }

/*    public void startdialog(){
        //カスタムビューの設定
        final LayoutInflater inflater = LayoutInflater.from(CreateFeedback.this);
        final View view = inflater.inflate(R.layout.achievement, null);

        //AlertDialog生成
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//タイトル設定
        builder.setTitle("達成率を入力");
//レイアウト設定
        builder.setView(view);
//ＯＫボタン設定
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                seekBar = (SeekBar) view.findViewById(R.id.seekbar);
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
                                tassei = seekBar.getProgress();


//                                String title = kari;
//                                String comment = commentEditText.getText().toString();
//                                float satisfaction = manzoku;
//

//                                Date today = new Date();
//                                SimpleDateFormat day = new SimpleDateFormat("MM-dd", Locale.JAPANESE);
//                                String logdate = day.format(today);
//
//                               save2(title, comment, satisfaction, logdate);



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


        });

//Cancelボタン設定
        builder.setNegativeButton("cancel",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
//キャンセルなので何もしない
            }
        });
//ダイアログの表示
        builder.create().show();



    }
*/

    public void create(View v){

//            startdialog();
        String title = kari;
        String comment = commentEditText.getText().toString();
        float satisfaction = manzoku;
        int achievement = tassei;


        Date today = new Date();
        SimpleDateFormat day = new SimpleDateFormat("MM-dd", Locale.JAPANESE);
        String logdate = day.format(today);




        save2(title, comment, satisfaction, logdate, achievement);

        finish();


    }



}






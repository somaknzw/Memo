package android.lifeistech.com.memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    }


    public void save2(final String title, final String comment, final float satisfaction, final String logdate){

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


        String title = kari;
        String comment = commentEditText.getText().toString();
        float satisfaction = manzoku;

        Date today = new Date();
        SimpleDateFormat day = new SimpleDateFormat("MM-dd", Locale.JAPANESE);
        String logdate = day.format(today);

        save2(title, comment, satisfaction, logdate);


        finish();
    }


    }



package android.lifeistech.com.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class FeedbackList extends AppCompatActivity {

    public Realm realm;
    public ListView feed_back_list;
    public ProgressBar progressBar;

    public String test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackview);

        realm = Realm.getDefaultInstance();

        feed_back_list = (ListView)findViewById(R.id.listView2);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

    }

    public void setFeedbacktList(){

        //realmから読み取る ここでタイトルから絞らせる
        RealmResults<Project> results = realm.where(Project.class).equalTo("title", getIntent().getStringExtra("title")).isNull("updateDate").findAll();
        List<Project> items = realm.copyFromRealm(results);

        Intent intent = getIntent();
        test = intent.getStringExtra("title");

        FeedbackAdapter adapter = new FeedbackAdapter(this, R.layout.project_layout, items);

        feed_back_list.setAdapter(adapter);

//      今からachievementから数値入手、それをバーに表示
        progressBar.setMax(100); // 水平プログレスバーの最大値を設定
        progressBar.setProgress(20); // 水平プログレスバーの値を設定　ここはちょいいじる
    }

    @Override
    protected void onResume() {
        super.onResume();

        setFeedbacktList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void gocreatefb(View v){
        Intent intent = new Intent(FeedbackList.this, CreateFeedback.class);
        intent.putExtra("title", test);
        startActivity(intent);

    }


}

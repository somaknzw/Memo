package android.lifeistech.com.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class FeedbackList extends AppCompatActivity {

    public Realm realm;
    public ListView feed_back_list;

    public String test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackview);

        realm = Realm.getDefaultInstance();

        feed_back_list = (ListView)findViewById(R.id.listView2);

    }

    public void setFeedbacktList(){

        //realmから読み取る ここでタイトルから絞らせる
        RealmResults<Project> results = realm.where(Project.class).equalTo("title", getIntent().getStringExtra("title")).findAll();
        List<Project> items = realm.copyFromRealm(results);

        Intent intent = getIntent();
        test = intent.getStringExtra("title");

        FeedbackAdapter adapter = new FeedbackAdapter(this, R.layout.project_layout, items);

        feed_back_list.setAdapter(adapter);
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

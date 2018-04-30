package android.lifeistech.com.memo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;

public class CreateActivity extends AppCompatActivity {

    //Realm型変数の宣言
    public Realm realm;

    public EditText titleEditText;
    public EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //realmを開く
        realm = Realm.getDefaultInstance();

        //関連付け
        titleEditText = (EditText)findViewById(R.id.titleEditText);
        contentEditText = (EditText)findViewById(R.id.contentEditText);

    }

    //データをrealmに保存
    public void save(final String title, final String updateDate, final String content){

        //メモを保存
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgrealm) {
                Memo memo = realm.createObject(Memo.class);
                memo.title = title;
                memo.updateDate = updateDate;
                memo.content = content;
            }
        });
    }

    //EditTextに入力されたデータを元にMemoをとる
    public void create(View v){
        //タイトルを取得
        String title = titleEditText.getText().toString();

        //日付を取得
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String updateDate = sdf.format(date);

        //内容取得
        String content = contentEditText.getText().toString();

//        //出力してみる
//        check(title,updateDate,content);

        //保存する
        save(title, updateDate, content);

        //終了する
        finish();
    }

    private void check(String title, String updateDate, String content) {

        Memo memo = new Memo();
        memo.title = title;
        memo.updateDate = updateDate;
        memo.content = content;

        Log.d("Memo", memo.title);
        Log.d("Memo", memo.updateDate);
        Log.d("Memo", memo.content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //realmを閉じる
        realm.close();
    }
}
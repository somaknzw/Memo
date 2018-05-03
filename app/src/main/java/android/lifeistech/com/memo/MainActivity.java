package android.lifeistech.com.memo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public Realm realm;
    public ListView ListView;
    public EditText titleEditText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //realmを開く
        realm = Realm.getDefaultInstance();

        ListView = (ListView)findViewById(R.id.listView);




        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Memo memo = (Memo) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("updateDate", memo.updateDate);
                startActivity(intent);

            }
        });
    }


    public void setMemoList(){

        //realmから読み取る
        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        List<Memo> items = realm.copyFromRealm(results);

        MemoAdapter adapter = new MemoAdapter(this, R.layout.layout_item_memo, items);

        ListView.setAdapter(adapter);
    }



    @Override
    protected void onResume() {
        super.onResume();

        setMemoList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //データをrealmに保存というメソッド
    public void save(final String title, final String updateDate, final String content){

        //メモを保存
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.title = title;
                memo.updateDate = updateDate;
                memo.content = content;
            }
        });
    }




    public void create(View v){


        //カスタムビューの設定
    final LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
    final View view = inflater.inflate(R.layout.dialog_1, null);

        //AlertDialog生成
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);

//タイトル設定
              builder.setTitle("Project Name");

//レイアウト設定
            builder.setView(view);

//ＯＫボタン設定
          builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int which){


          titleEditText2 = (EditText) view.findViewById(R.id.titleEditText2);


         String title = titleEditText2.getText().toString();

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
            String updateDate = sdf.format(date);

            String content = "";

         save(title, updateDate, content);

        setMemoList();


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




//        Intent intent = new Intent(this, CreateActivity.class);
//        startActivity(intent);


}

package android.lifeistech.com.memo;

import io.realm.RealmObject;

public class Project extends RealmObject{

    public String projectname;
    public String comment;
    public String manzoku;
    public String tassei;

    //カスタムビューの設定
    // LayoutInflater inflater
    //       = LayoutInflater.from(MainActivity.this);
    //View view = inflater.inflate(R.layout.dialog_1, null);

//AlertDialog生成
    //AlertDialog.Builder builder = new AlertDialog.Builder(this);

//タイトル設定
    //builder.setTitle("Project Name");

//レイアウト設定
    //builder.setView(view);

//ＯＫボタン設定
    //builder.setPositiveButton("ok",new DialogInterface.OnClickListener(){
    //  public void onClick(DialogInterface dialog, int which){
//                EditText titleEditText;
//                titleEditText = (EditText)findViewById(R.id.titleEditText);
//
//                public void save(final String title){

//                    //メモを保存
//                    realm.executeTransaction(new Realm.Transaction() {
//                        @Override
//                        public void execute(Realm realm) {
//                            Memo memo = realm.createObject(Memo.class);
//                            memo.title = title;
//                        }
//                    });
    //               }
//                memo.settitle=(title);
//
//                String title = titleEditText.getText().toString();
//                save(title);
//                finish();
    //          }
    //    });


//Cancelボタン設定
    //       builder.setNegativeButton("cancel",new DialogInterface.OnClickListener(){
    //          public void onClick(DialogInterface dialog, int which){
//キャンセルなので何もしない
    //        }
    //  });

//ダイアログの表示
    // builder.create().show();

}

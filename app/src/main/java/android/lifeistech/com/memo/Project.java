package android.lifeistech.com.memo;
//ここはまだなにも使ってない
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmObject;

public class Project extends RealmObject {

    public String projectname;
    public String comment;
    public String manzoku;
    public String tassei;

}


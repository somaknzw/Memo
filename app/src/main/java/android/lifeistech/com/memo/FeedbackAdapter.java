package android.lifeistech.com.memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FeedbackAdapter extends ArrayAdapter<Project> {

    private LayoutInflater layoutinflater;

    FeedbackAdapter(Context context, int textViewResourceId, List<Project> objects) {
        super(context, textViewResourceId, objects);
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Project detail = getItem(position);

        if (convertView == null) {
            convertView = layoutinflater.inflate(R.layout.project_layout, null);
        }

        TextView commentText = (TextView) convertView.findViewById(R.id.titleText);
        TextView achievementText = (TextView) convertView.findViewById(R.id.achienementText);

        commentText.setText(detail.comment);
        achievementText.setText(detail.achievement);

        return convertView;
    }
}
package android.lifeistech.com.memo;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import android.widget.ProgressBar;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    protected List<String> dataset;
    private View.OnClickListener onItemViewClickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView textView;


        public ViewHolder(View view){
            super(view);
            textView = (TextView)view.findViewById(R.id.commentText);
        }
    }

    //コンストラクタでデータ渡す
    public RecyclerAdapter(List<String> myDataset){
        dataset = myDataset;
    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickLitener){
        this.onItemViewClickListener = onItemViewClickLitener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //新しくView作る
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_title_layout, parent, false);

        //viewにクリックリスナーを付ける
        if(onItemViewClickListener !=null){
            view.setOnClickListener(onItemViewClickListener);
        }

        ViewHolder vh =new ViewHolder(view);
        return vh;
    }

    //viewの中のデータを変更
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        //セットするデータの取得

        String text = dataset.get(position);
        //viewholderの中のviewの中のデータを変更
        holder.textView.setText(text);
    }

    //データの数を返す
    @Override
    public int getItemCount(){
        return dataset.size();
    }



}





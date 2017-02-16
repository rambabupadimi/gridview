package com.gridview;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ramu on 16/02/17.
 */

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Map> list;
    private String TAG = "HealthProductAdapter.java";


    public GridViewAdapter(Context context, List<Map> list) {
        this.context = context;
        this.list = list;
       }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.gridviewadapter, parent, false);
        }
        else {
            v = (View) convertView;
        }
        HashMap hashMap = (HashMap) list.get(position);
        ResizableImageView imageView = (ResizableImageView) v.findViewById(R.id.grid_imageview);
        TextView title = (TextView) v.findViewById(R.id.grid_title);
        TextView description = (TextView) v.findViewById(R.id.grid_description);
        title.setText(hashMap.get("title").toString());
        description.setText(hashMap.get("repourl").toString());
        Picasso.with(context)
                .load(hashMap.get("imgurl").toString())
                .into(imageView);
        return v;

    }

}


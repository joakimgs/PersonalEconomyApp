package ae3808.mah.se.ekonomiappen;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Joakim on 10/20/2015.
 */
public class  OverviewAdapter extends ArrayAdapter<Values> {
    private LayoutInflater inflater;

    public OverviewAdapter(Context context, Values[] objects) {
        super(context, R.layout.overview_row2, objects );
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

 public View getView(int position, View convertView, ViewGroup parent) {
     ViewHolder holder;
     Values objects = getItem(position);


     if (convertView == null) {
         convertView = inflater.inflate(R.layout.overview_row2, parent, false);
         holder = new ViewHolder();
         holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
         holder.tvType = (TextView) convertView.findViewById(R.id.tvType);
         holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
         holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
         holder.tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
         convertView.setTag(holder);
     } else {
         holder = (ViewHolder)convertView.getTag();
     }
     if (objects.getType().equals("Mat")) {
         holder.ivIcon.setImageResource(R.drawable.foodbagg);
     }
     if (objects.getType().equals("Hobby")) {
         holder.ivIcon.setImageResource(R.drawable.football);
     }
     if (objects.getType().equals("Resa")) {
         holder.ivIcon.setImageResource(R.drawable.airplanee);
     }
     if (objects.getType().equals("Hushåll")) {
         holder.ivIcon.setImageResource(R.drawable.house);
     }
     if (objects.getType().equals("Övrigt")) {
         holder.ivIcon.setImageResource(R.drawable.other);
     }
     Log.d("", objects.getType());
     holder.tvDate.setText(objects.getDate());
     holder.tvTitle.setText(objects.getTitle());
     holder.tvAmount.setText(objects.getAmount());

     return convertView;
 }

    private class ViewHolder {
        TextView tvType;
        TextView tvDate;
        TextView tvTitle;
        TextView tvAmount;
        ImageView ivIcon;
    }
}

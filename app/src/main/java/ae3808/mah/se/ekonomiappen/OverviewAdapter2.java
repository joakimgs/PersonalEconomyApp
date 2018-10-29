package ae3808.mah.se.ekonomiappen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Joakim on 10/20/2015.
 */
public class OverviewAdapter2 extends ArrayAdapter<Values> {
    private LayoutInflater inflater;

    public OverviewAdapter2(Context context, Values[] objects) {
        super(context, R.layout.overview_row, objects );
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

 public View getView(int position, View convertView, ViewGroup parent) {
     ViewHolder holder;
     Values objects = getItem(position);
     if (convertView == null) {
         convertView = inflater.inflate(R.layout.overview_row, parent, false);
         holder = new ViewHolder();
         holder.tvType = (TextView) convertView.findViewById(R.id.tvType);
         holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
         holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
         holder.tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
         convertView.setTag(holder);
     } else {
         holder = (ViewHolder)convertView.getTag();
     }
     holder.tvType.setText(objects.getType());
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

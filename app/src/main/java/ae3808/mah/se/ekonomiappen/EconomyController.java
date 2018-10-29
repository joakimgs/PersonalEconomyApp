package ae3808.mah.se.ekonomiappen;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Joakim on 10/24/2015.
 */
public class EconomyController {
    private EconomyActivity economyActivity;
    private Values[] values;
    private Parcelable[] parcelables;

    public EconomyController(EconomyActivity economyActivity, Intent intent) {
        this.economyActivity = economyActivity;

        parcelables = intent.getParcelableArrayExtra(Controller.VALUES);
        values = new Values[parcelables.length];
        for(int i=0; i<values.length; i++) {
            values[i] = (Values)parcelables[i];
        }
        economyActivity.setAdapter(new EconomyAdapter(economyActivity, values));
    }

    private class EconomyAdapter extends ArrayAdapter<Values> {
        private LayoutInflater inflater;

        public EconomyAdapter(Context context, Values[] values) {
            super(context, R.layout.overview_row, values);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Values values = getItem(position);
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.overview_row, parent, false);
                holder = new ViewHolder();
                holder.tvType = (TextView) convertView.findViewById(R.id.tvType);
                holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                holder.tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvType.setText(values.getType());
            holder.tvDate.setText(values.getDate());
            holder.tvTitle.setText(values.getType());
            holder.tvAmount.setText(values.getAmount());

            return convertView;
        }
    }

        private class ViewHolder {
            private TextView tvType;
            private TextView tvAmount;
            private TextView tvDate;
            private TextView tvTitle;
        }
    }

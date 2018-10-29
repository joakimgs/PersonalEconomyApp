package ae3808.mah.se.ekonomiappen;


import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {
    private LoginFragment loginFragment;
    private TextView tvSharedPref;
    private ListView lvIncome, lvExpense;
    private Controller controller;
    private Button btnClearDB;
    private RadioButton rbLastweek, rbLastmonth, rbAlltime;
    private String[] content = {"BILD          , BELOPP,             SALDO"};
    private ArrayAdapter<Values> valuesArrayAdapterr,valuesArrayAdapterrr;
    public OverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_overview, container, false);

        initOverviewComponents(view);
        registerListeners();
        initTagListener();
         return view;
    }

    public void initOverviewComponents(View view) {
        btnClearDB = (Button) view.findViewById(R.id.btnClearDB);
        lvIncome = (ListView) view.findViewById(R.id.lvIncome);
        lvExpense = (ListView) view.findViewById(R.id.lvExpense);
        rbLastweek = (RadioButton) view.findViewById(R.id.rbLastweek);
        rbLastmonth = (RadioButton) view.findViewById(R.id.rbLastmonth);
        rbAlltime = (RadioButton) view.findViewById(R.id.rbAlltime);
        tvSharedPref = (TextView) view.findViewById(R.id.tvSharedPref);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void registerListeners() {
        btnClearDB.setOnClickListener(new BL());
    }

    private class BL implements View.OnClickListener {
        public void onClick(View v) {
            controller.deleteAll();
        }
    }

    public void onResume(){
        super.onResume();
        lvIncome.setAdapter(valuesArrayAdapterr);
        lvExpense.setAdapter(valuesArrayAdapterrr);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginFragment", Activity.MODE_PRIVATE);
        String tvSharedPrefText = sharedPreferences.getString("Sharedpref", null);
        if(tvSharedPref!=null) {
            tvSharedPref.setText(tvSharedPrefText);
        }
    }

    public void setValues(ArrayAdapter<Values> vAdapter) {
        valuesArrayAdapterr = vAdapter;
    }
    public void setValues2(ArrayAdapter<Values> vAdapter2) {
        valuesArrayAdapterrr = vAdapter2;
    }

    public void initTagListener() {
        TagListener tagListener = new TagListener();
        ExpenseListener expenseListener = new ExpenseListener();
        lvIncome.setOnItemClickListener(tagListener);
        lvExpense.setOnItemClickListener(expenseListener);
    }

    private class TagListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String message = "" + valuesArrayAdapterr.getItem(position).getType().toString() + ", " +
                    valuesArrayAdapterr.getItem(position).getDate().toString() + ", " + valuesArrayAdapterr.getItem
                    (position).getTitle().toString() + ", " + valuesArrayAdapterr.getItem(position).getAmount();
            Dialog dialog = new Dialog(getActivity());
            TextView tv = new TextView(getActivity());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            tv.setPadding(20, 20, 20, 20);
            tv.setBackgroundColor(Color.LTGRAY);
            tv.setText(message);
            dialog.setContentView(tv);
            dialog.show();
        }
    }

    private class ExpenseListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String message = "" + valuesArrayAdapterrr.getItem(position).getType().toString() + ", " +
                    valuesArrayAdapterrr.getItem(position).getDate().toString() + ", " + valuesArrayAdapterrr.getItem
                    (position).getTitle().toString() + ", " + valuesArrayAdapterrr.getItem(position).getAmount();
            Dialog dialog = new Dialog(getActivity());
            TextView tv = new TextView(getActivity());
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            tv.setPadding(20, 20, 20, 20);
            tv.setBackgroundColor(Color.LTGRAY);
            tv.setText(message);
            dialog.setContentView(tv);
            dialog.show();
        }
    }
}

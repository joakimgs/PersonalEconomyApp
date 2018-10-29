package ae3808.mah.se.ekonomiappen;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private Controller controller;
    private OverviewFragment overviewFragment;
    private MainActivity mainActivity;
    private Button btnIncome, btnExpense, btnOverview;
    private EditText etName;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initLoginComponents(view);
        registerListeners();
        return view;
    }

    public void onPause() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginFragment", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Sharedpref", etName.getText().toString());
        editor.apply();
        super.onPause();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void initLoginComponents(View view) {
        btnIncome = (Button)view.findViewById(R.id.btnIncome);
        btnExpense = (Button)view.findViewById(R.id.btnExpense);
        btnOverview = (Button)view.findViewById(R.id.btnOverview);
        etName = (EditText)view.findViewById(R.id.etName);
    }

    private void registerListeners() {
        btnIncome.setOnClickListener(new BL());
        btnExpense.setOnClickListener(new BL());
        btnOverview.setOnClickListener(new BL());
    }

    private class BL implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnIncome : controller.incomeFragment();
                    break;
                case R.id.btnExpense : controller.expenseFragment();
                    break;
                case R.id.btnOverview : controller.overviewFragment();
            }
        }
    }
}

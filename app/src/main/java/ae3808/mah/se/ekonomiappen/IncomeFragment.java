package ae3808.mah.se.ekonomiappen;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {
    private Controller controller;
    private Button btnIncome;
    private EditText etDate, etTitle, etAmount;
    private RadioButton rbSalary, rbOther;
    private RadioGroup rgIncome;



    public IncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        initIncomeComponents(view);
        registerListeners();
        return view;
    }

    public void initIncomeComponents(View view) {
        btnIncome = (Button) view.findViewById(R.id.btnIncome);
        rbOther = (RadioButton) view.findViewById(R.id.rbOther);
        rbSalary = (RadioButton) view.findViewById(R.id.rbSalary);
        etDate = (EditText) view.findViewById(R.id.etDate);
        etTitle = (EditText) view.findViewById(R.id.etTitle);
        etAmount = (EditText) view.findViewById(R.id.etAmount);
        rgIncome = (RadioGroup) view.findViewById(R.id.rgIncome);
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }


    private void registerListeners() {
        btnIncome.setOnClickListener(new BL());
    }


    private class BL implements View.OnClickListener {
        public void onClick(View v) {

//            controller.deleteAll();
            controller.addValues2(getStringtoDB2(), etDate.getText().toString(), etTitle.getText().toString(),
                    etAmount.getText().toString());
        }
    }

    private String getStringtoDB2() {
        int radioButtonID = rgIncome.getCheckedRadioButtonId();
        View radioButton = getView().findViewById(radioButtonID);
        String category = "";

        if(rgIncome.indexOfChild(radioButton) == 0) {
            category = "Lön";
        }

        if(rgIncome.indexOfChild(radioButton) == 1) {
            category = "Övrigt";
        }
        return category;
    }


}

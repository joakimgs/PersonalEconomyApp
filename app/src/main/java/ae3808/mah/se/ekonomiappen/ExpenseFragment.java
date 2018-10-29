package ae3808.mah.se.ekonomiappen;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseFragment extends Fragment {
    private Controller controller;
    private RadioButton rbGroceries, rbFootball, rbAirplane, rbHouse, rbOther;
    private Button btnExpense;
    private RadioGroup rgExpense;
    private EditText etDate, etTitle, etPrice;
    private ImageView ivGroceries, ivFootball, ivAirplane, ivHouse, ivOther;
    private TextView tvTypee;

    public ExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        initExpenseComponents(view);
        registerListeners();
        return view;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void initExpenseComponents(View view) {
        rbGroceries = (RadioButton) view.findViewById(R.id.rbGroceries);
        rbFootball = (RadioButton) view.findViewById(R.id.rbFootball);
        rbAirplane = (RadioButton) view.findViewById(R.id.rbAirplane);
        rbHouse = (RadioButton) view.findViewById(R.id.rbHouse);
        rbOther = (RadioButton) view.findViewById(R.id.rbOther);
        etDate = (EditText) view.findViewById(R.id.etDate);
        etTitle = (EditText) view.findViewById(R.id.etTitle);
        etPrice = (EditText) view.findViewById(R.id.etPrice);
        ivGroceries = (ImageView) view.findViewById(R.id.ivGroceries);
        ivFootball = (ImageView) view.findViewById(R.id.ivFootball);
        ivAirplane = (ImageView) view.findViewById(R.id.ivAirplane);
        ivHouse = (ImageView) view.findViewById(R.id.ivHouse);
        ivOther = (ImageView) view.findViewById(R.id.ivOther);
        btnExpense = (Button) view.findViewById(R.id.btnExpense);
        rgExpense = (RadioGroup) view.findViewById(R.id.rgExpense);
     }

    private void registerListeners() {
        btnExpense.setOnClickListener(new BL());
    }

    private class BL implements View.OnClickListener {
        public void onClick(View v) {

            controller.addValues(getStringtoDB(), etDate.getText().toString(), etTitle.getText().toString(),
                    etPrice.getText().toString());

        }
    }

    private String getStringtoDB(){
        int radioButtonID = rgExpense.getCheckedRadioButtonId();
        View radioButton = getView().findViewById(radioButtonID);
        String category = "";
        if(rgExpense.indexOfChild(radioButton) == 0) {
            category = "Mat";
        }

        if(rgExpense.indexOfChild(radioButton) == 1) {
            category = "Hobby";
        }

        if(rgExpense.indexOfChild(radioButton) == 2) {
            category = "Resa";
        }

        if(rgExpense.indexOfChild(radioButton) == 3) {
            category = "Hushåll";
        }

        if(rgExpense.indexOfChild(radioButton) == 4) {
            category = "Övrigt";
        }
        return category;
    }
}

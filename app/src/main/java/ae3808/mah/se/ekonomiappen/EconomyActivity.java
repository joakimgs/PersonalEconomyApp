package ae3808.mah.se.ekonomiappen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EconomyActivity extends Activity {
    private ListView lvIncome;
    private ListView lvExpense;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy);
        lvIncome = (ListView)findViewById(R.id.lvIncome);
        lvExpense = (ListView)findViewById(R.id.lvExpense);

        Intent intent = getIntent();
        new EconomyController(this,intent);
    }

    public void setAdapter(ArrayAdapter<Values> overviewAdapter) {
        lvIncome.setAdapter(overviewAdapter);
        lvExpense.setAdapter(overviewAdapter);
    }

}

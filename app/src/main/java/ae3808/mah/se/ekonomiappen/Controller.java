package ae3808.mah.se.ekonomiappen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.security.spec.ECField;

/**
 * Created by Joakim on 10/13/2015.
 */
public class Controller {
    public final static String VALUES = "ae3808.mah.se.ekonomiappen.VALUES";
    private EconomyDB economyDB;
    private IncomeFragment incomeFragment;
    private MainActivity mainActivity;
    private LoginFragment loginFragment;
    private ExpenseFragment expenseFragment;
    private OverviewFragment overviewFragment;

    public Controller(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        loginFragment = new LoginFragment();
        mainActivity.initComponents(loginFragment);
        loginFragment.setController(this);
        economyDB = new EconomyDB(mainActivity);
    }

    public void incomeFragment() {
            incomeFragment = new IncomeFragment();
            incomeFragment.setController(this);
            FragmentManager fragmentManager = mainActivity.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.startContainer, (Fragment) incomeFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
    }


    public void expenseFragment() {
        expenseFragment = new ExpenseFragment();
        expenseFragment.setController(this);
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.startContainer, (Fragment) expenseFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void overviewFragment() {
        overviewFragment = new OverviewFragment();
        overviewFragment.setController(this);
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.startContainer, (Fragment) overviewFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        overviewFragment.setValues(new OverviewAdapter2(this.mainActivity, showOverview()));
        overviewFragment.setValues2(new OverviewAdapter(this.mainActivity, showOverview2()));

    }


//    public void addItems() {
//        Values[] valuess = {new Values("1", "Hushållsost", "-100", 5000),
//        new Values("2", "Falukorv", "-50", 4950),
//        new Values("3", "Mjölk", "-20", 4930),
//        new Values("4", "mackor", "-30", 4000)};
//        for (Values value : valuess) {
//            addValues(value);
//        }
//        valuess[4].setBalance(valuess[4].getBalance() + 500);
//        updateValues(valuess[4]);
//
//}

    public void addValues(String category, String etDate, String etTitle, String etPrice) {
        Log.d("", category);
        Log.d("", etDate);
        Log.d("", etTitle);
        Log.d("", etPrice);
        SQLiteDatabase db = economyDB.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EconomyDB.COLUMN_CATEGORY2, category);
        cv.put(EconomyDB.COLUMN_DATE2, etDate);
        cv.put(EconomyDB.COLUMN_TITLE2, etTitle);
        cv.put(EconomyDB.COLUMN_AMOUNT2, etPrice);
        db.insert(EconomyDB.TABLE_EXPENSE, "", cv);
    }

    public void addValues2(String category, String etDate, String etTitle, String etAmount) {
        SQLiteDatabase db = economyDB.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EconomyDB.COLUMN_CATEGORY, category);
        cv.put(EconomyDB.COLUMN_DATE, etDate);
        cv.put(EconomyDB.COLUMN_TITLE, etTitle);
        cv.put(EconomyDB.COLUMN_AMOUNT, etAmount);
        db.insert(EconomyDB.TABLE_INCOME, "", cv);
        Log.d("", category);
        Log.d("", etDate);
        Log.d("", etTitle);
        Log.d("", etAmount);
    }
    public Values[] showOverview() {
        int idIndex, categoryIndex, amountIndex, dateIndex, titleIndex;

        Values[] values = null;
        SQLiteDatabase db = economyDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EconomyDB.TABLE_INCOME + " ORDER BY " + EconomyDB.COLUMN_ID, null);
        values = new Values[cursor.getCount()];
        idIndex = cursor.getColumnIndex(EconomyDB.COLUMN_ID);
        categoryIndex  = cursor.getColumnIndex(EconomyDB.COLUMN_CATEGORY);
        dateIndex  = cursor.getColumnIndex(EconomyDB.COLUMN_DATE);
        titleIndex  = cursor.getColumnIndex(EconomyDB.COLUMN_TITLE);
        amountIndex = cursor.getColumnIndex(EconomyDB.COLUMN_AMOUNT);

        for (int i = 0; i < values.length; i++) {
            cursor.moveToPosition(i);
            values[i] = new Values(cursor.getString(idIndex),
                    cursor.getString(categoryIndex),
                    cursor.getString(dateIndex),
                    cursor.getString(titleIndex),
                    cursor.getString(amountIndex));
        }
        return values;
    }
    public Values[] showOverview2() {
        int idIndex, categoryIndex, amountIndex, dateIndex, titleIndex;

        Values[] values = null;
        SQLiteDatabase db = economyDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EconomyDB.TABLE_EXPENSE + " ORDER BY " + EconomyDB.COLUMN_ID2, null);
        values = new Values[cursor.getCount()];
        idIndex = cursor.getColumnIndex(EconomyDB.COLUMN_ID);
        categoryIndex  = cursor.getColumnIndex(EconomyDB.COLUMN_CATEGORY);
        dateIndex  = cursor.getColumnIndex(EconomyDB.COLUMN_DATE);
        titleIndex  = cursor.getColumnIndex(EconomyDB.COLUMN_TITLE);
        amountIndex = cursor.getColumnIndex(EconomyDB.COLUMN_AMOUNT);

        for (int i = 0; i < values.length; i++) {
            cursor.moveToPosition(i);
            values[i] = new Values(cursor.getString(idIndex),
                    cursor.getString(categoryIndex),
                    cursor.getString(dateIndex),
                    cursor.getString(titleIndex),
                    cursor.getString(amountIndex));
        }
        return values;
    }
    public void deleteAll() {
        SQLiteDatabase db = economyDB.getWritableDatabase();
        db.delete(economyDB.TABLE_INCOME, null, null);
        db.delete(economyDB.TABLE_EXPENSE, null, null);
    }
}

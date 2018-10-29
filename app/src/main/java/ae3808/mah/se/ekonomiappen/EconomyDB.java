package ae3808.mah.se.ekonomiappen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Joakim on 10/21/2015.
 */
public class EconomyDB extends SQLiteOpenHelper {
    public static final String TABLE_INCOME = "income";
    public static final String TABLE_EXPENSE = "expense";

    public static final String COLUMN_ID2 = "id";
    public static final String COLUMN_CATEGORY2 = "category";
    public static final String COLUMN_DATE2 = "date";
    public static final String COLUMN_TITLE2 = "title";
    public static final String COLUMN_AMOUNT2 = "amount";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AMOUNT = "amount";

    private static final String DATABASE_NAME = "economy.db";
    private static final int DATABASE_VERSION = 1;

    private static final String dbExpense = "create table " + TABLE_EXPENSE + "(" + COLUMN_ID2 + " integer primary key AUTOINCREMENT, " +
            COLUMN_DATE2 + " text not null, " + COLUMN_AMOUNT2 + " text not null, " + COLUMN_TITLE2 + " text not null, " +
            COLUMN_CATEGORY2 + " text not null);";

    private static final String dbIncome = "create table " + TABLE_INCOME + "(" + COLUMN_ID + " integer primary key AUTOINCREMENT, " +
            COLUMN_CATEGORY + " text not null, " + COLUMN_DATE + " text not null, " + COLUMN_TITLE + " text not null, " +
            COLUMN_AMOUNT + " text not null);";
    public EconomyDB (Context context) {
        super(context, dbExpense, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbIncome);
        db.execSQL(dbExpense);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(EconomyDB.class.getName(), "Upgrading database from version " + oldVersion + " to" + newVersion +
                ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);
        onCreate(db);
    }




}

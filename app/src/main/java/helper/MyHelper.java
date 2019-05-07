package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.Item;
import model.KOT;

import java.util.ArrayList;
import java.util.List;

public class MyHelper extends SQLiteOpenHelper {

    private static final String databaseNAme = "DictionaryDB";
    private static final int dbVersion = 1;
    private static final String tblKOT = "tblKOT";
    private static final String kotId= "kotId";
    private static final String kotItem= "kotItem";
    private static final String kotQuantity= "kotQuantity";
    private static final String kotTable= "kotTable";

    private static final String tblItem= "tblItem";
    private static final String itemId= "itemId";
    private static final String itemName= "itemName";




    public MyHelper(Context context) {
        super(context, databaseNAme, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+tblKOT +
                " ( " + kotId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + kotItem + " TEXT,"
                + kotQuantity + " TEXT,"
                + kotTable + " TEXT)";
        db.execSQL(query);

        String query1 = "CREATE TABLE "+tblItem+
                " ( " + itemId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + itemName+ " TEXT)";
        db.execSQL(query1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static long InsertKot(String kotItemName, String kotTotalQuantity, String table, SQLiteDatabase db){
        long id;
        ContentValues contentValues = new ContentValues();
        contentValues.put(kotItem, kotItemName);
        contentValues.put(kotQuantity, kotTotalQuantity);
        contentValues.put(kotTable, table);
        id = db.insert(tblKOT,null,contentValues);

        return id;
        }

        public List<KOT> GetAllKot (SQLiteDatabase db){
        List<KOT> kotList = new ArrayList<>();
        String[] columns = {kotId, kotItem,kotQuantity, kotTable};
        Cursor cursor = db.query(tblKOT, columns, null, null, null, null, null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                kotList.add(new KOT(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }
        }
        return kotList;
        }

        public List<KOT> GetkotByTableName(String table, SQLiteDatabase db){
        List<KOT> kotList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+ tblKOT+ " where " + kotTable + " = ?", new String[] {table});
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                kotList.add(new KOT(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }
        }
        return kotList;
        }

        public List<KOT> UpdateKotById(int id, SQLiteDatabase db){
        List<KOT> kotList = new ArrayList<>();

        return kotList;
        }


    public static long InsertItem(String newItemName, SQLiteDatabase db){
        long id;

        ContentValues contentValues = new ContentValues();
        contentValues.put(itemName,newItemName);
        id=db.insert(tblItem, null, contentValues);
        return id;
        }

        public List<Item> GetAllItems (SQLiteDatabase db){
        List<Item> itemList = new ArrayList<>();
        String[] columns = {itemId, itemName};
        Cursor cursor = db.query(tblItem, columns,null,null,null,null,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                itemList.add(new Item(cursor.getInt(0),cursor.getString(1)));
            }
        }
        return itemList;
        }

        public List<Item> GetItemByName(String item_name, SQLiteDatabase db){
        List<Item> itemList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + tblItem + " where " + itemName + " = ?", new String[]{item_name});
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                itemList.add(new Item(cursor.getInt(0),cursor.getString(1)));
            }
        }
        return itemList;
        }
}

package com.example.root.databaseusesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by root on 17. 8. 12.
 */

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="productDB.db";
    public static final String TABLE_PRODUCTS="products";

    public static final String COLUMN_ID="_id";
    public static final String COLUMN_PRODUCTNAME="productname";
    public static final String COLUMN_QUANTITY="quantity";

    public MyDBHandler(Context context,String name,SQLiteDatabase.CursorFactory factory,
                       int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCTS_TABLE="CREATE TABLE "+TABLE_PRODUCTS+"("
                +COLUMN_ID+" TEXT ,"+COLUMN_PRODUCTNAME
                +" TEXT,"+COLUMN_QUANTITY+" INTEGER"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        onCreate(db);
    }
    public void addProduct(Product product){
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID,product.get_id());
        values.put(COLUMN_PRODUCTNAME,product.get_productname());
        values.put(COLUMN_QUANTITY,product.get_quantity());



        //values.put(COLUMN_ID,"id");
        //values.put(COLUMN_PRODUCTNAME,"name");
        //values.put(COLUMN_QUANTITY,11);


        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PRODUCTS,null,values);
        db.close();
    }

    public Product findProduct(String productname){
        String query="SELECT * FROM "+TABLE_PRODUCTS+" WHERE "+
                COLUMN_PRODUCTNAME + " = \""+productname+ "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Log.d("logger",query);


        Product product = new Product();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            product.set_id(cursor.getString(8));
            product.set_productname(cursor.getString(1));
            product.set_quantity(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else{
            product=null;
        }

        db.close();
        return product;
    }

    public boolean deleteProduct(String productname){
        boolean result=false;
        String query="Select * FROM "+TABLE_PRODUCTS+" WHERE "+
                COLUMN_PRODUCTNAME+" = \""+productname+"\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        Product product = new Product();

        if(cursor.moveToFirst()){
            product.set_id(cursor.getString(0));
            db.delete(TABLE_PRODUCTS,COLUMN_ID +" = ?",new String[] {String.valueOf(product.get_id())});
            cursor.close();
            result=true;
        }
        db.close();
        return result;

    }



}

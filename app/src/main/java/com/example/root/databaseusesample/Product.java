package com.example.root.databaseusesample;

/**
 * Created by root on 17. 8. 12.
 */

public class Product {

    private String _id;
    private String _productname;
    private int _quantity;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_productname() {
        return _productname;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }

    public int get_quantity() {
        return _quantity;
    }

    public void set_quantity(int _quantity) {
        this._quantity = _quantity;
    }

    public Product(){

    }

    public Product(String id,String productname,int quantity){
        this._id=id;
        this._productname=productname;
        this._quantity=quantity;
    }

    public Product(String productname, int quantity){
        this._productname=productname;
        this._quantity=quantity;
    }

}

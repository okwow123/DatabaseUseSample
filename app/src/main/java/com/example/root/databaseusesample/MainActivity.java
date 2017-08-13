package com.example.root.databaseusesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText idView;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = (EditText) findViewById(R.id.ProductId);
        productBox = (EditText) findViewById(R.id.ProductName);
        quantityBox = (EditText)findViewById(R.id.ProductQuantity);


    }

    public void newProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        int quantity = Integer.parseInt(quantityBox.getText().toString());

        Product product = new Product(idView.getText().toString() ,productBox.getText().toString(),quantity);
        dbHandler.addProduct(product);

        idView.setText("");
        productBox.setText("");
        quantityBox.setText("");
    }

    public void lookupProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        Product product = dbHandler.findProduct(productBox.getText().toString());


        if(product!=null){
            idView.setText(String.valueOf(product.get_id()));
            quantityBox.setText(String.valueOf(product.get_quantity()));
        }else{
            idView.setText("No Match Found");
        }
    }

    public void removeProduct(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        boolean result = dbHandler.deleteProduct(productBox.getText().toString());

        if(result){
            idView.setText("Record Deleted");
            productBox.setText("");
            quantityBox.setText("");
        }else{
            idView.setText("No Match Found");
        }
    }
}

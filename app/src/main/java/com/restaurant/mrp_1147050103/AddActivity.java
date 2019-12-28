package com.restaurant.mrp_1147050103;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.restaurant.mrp_1147050103.data.Constans;
import com.restaurant.mrp_1147050103.data.Session;
import com.restaurant.mrp_1147050103.model.RegisterResponse;
import com.restaurant.mrp_1147050103.utils.DialogUtils;

public class AddActivity extends AppCompatActivity {
    Session session;
    EditText name,category, link, address;
    Button addRestaurant;
    ProgressDialog progressDialog;
    String userID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        session = new Session(this);
        progressDialog = new ProgressDialog(this);
//        nama = getIntent().getStringExtra("nama");
        initBinding();
//        addRestaurant();
        initClick();
    }
    private void initBinding() {
        name = findViewById(R.id.name);
        category = findViewById(R.id.category);
        link = findViewById(R.id.link);
        address = findViewById(R.id.address);
        addRestaurant = findViewById(R.id.btn_add);
//        name.setText(nama);
    }
    private void initClick() {
        addRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addRestaurant();
                }
        });
    }
    public void addRestaurant() {
        DialogUtils.openDialog(this);

        AndroidNetworking.post(Constans.CREATE_RESTORAN)
                .addBodyParameter("userid",
                        session.getUserId())
                .addBodyParameter("namarm",
                        name.getText().toString())
                .addBodyParameter("kategori",
                        category.getText().toString())
                .addBodyParameter("link_foto",
                        link.getText().toString())
                .addBodyParameter("alamat",
                        address.getText().toString())
                .build()
                .getAsObject(RegisterResponse.class, new
                        ParsedRequestListener() {
                            @Override
                            public void onResponse(Object response) {
                                if (response instanceof RegisterResponse) {
                                    RegisterResponse res = (RegisterResponse)
                                            response;
                                    if (res.getStatus().equals("success")) {
                                        Toast.makeText(AddActivity.this,
                                                "Berhasil Tambah Restaurant", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(AddActivity.this,
                                                "Gagal Menambah", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                DialogUtils.closeDialog();
                            }
                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(AddActivity.this,
                                        "Terjadi kesalahan API", Toast.LENGTH_SHORT).show();
                                Toast.makeText(AddActivity.this,
                                        "Terjadi kesalahan API : "+anError.getCause().toString(),
                                        Toast.LENGTH_SHORT).show();
                                DialogUtils.closeDialog();
                            }
                        });
    }
}

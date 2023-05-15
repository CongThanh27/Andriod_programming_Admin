package com.example.appfood.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appfood.R;
import com.example.appfood.api.APIService;
import com.example.appfood.model.ProductModel;
import com.example.appfood.util.Constants;
import com.example.appfood.util.RealPathUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_Product extends AppCompatActivity {
    private EditText et_name;
    private EditText et_price;
    private EditText et_quantity;
    private EditText et_supplier;
    private EditText et_category;
    private EditText et_describe;
    private EditText et_trademark;
    private EditText et_origin;
    private EditText et_sex;
    private EditText et_skinproblems;
    private Button btn_choose_image;
    private ImageView iv_preview_image;

    private Button btn_add_product;

    private ProductModel productModel;

    public static final  int MY_REQUEST_CODE = 100;
    public static  final String TAG = Edit_Product.class.getName();
    private Uri mUri;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Intent intent = new Intent(Edit_Product.this, ProductManagerment.class);
            startActivity(intent);
        }
        else {
            productModel = (ProductModel) bundle.get("product");
            AnhXa();
            et_name.setText(productModel.getProduct_name());
            et_category.setText(productModel.getCategory());
            et_describe.setText(productModel.getDescription());
            et_origin.setText(productModel.getOrigin());
            et_price.setText(String.valueOf( productModel.getPrice()));
            et_quantity.setText(String.valueOf(productModel.getQuantity()));
            et_sex.setText((productModel.getSex()));
            et_skinproblems.setText(productModel.getSkinproblems());
            et_supplier.setText(productModel.getSupplier());
            et_trademark.setText(productModel.getTrademark());
            String correctedImagePath = productModel.getImage().replace("\\", "/");
            Glide.with(getApplicationContext()).load(Constants.url+"/" +correctedImagePath).into(iv_preview_image);
        }

        btn_choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckPermission();
            }
        });

        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultipartBody.Part imagePart;
                if(mUri!=null) {

                    String IMAGE_PATH = RealPathUtil.getRealPath(Edit_Product.this, mUri);


                    Log.e("ffff", IMAGE_PATH);

                    File file = new File(IMAGE_PATH);

                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                }
                else {

                     imagePart = null;
                }
                    RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"),String.valueOf(productModel.getId()));

                    RequestBody product_name = RequestBody.create(MediaType.parse("multipart/form-data"), et_name.getText().toString());
                    RequestBody priceold = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(productModel.getPrice()));
                    RequestBody price = RequestBody.create(MediaType.parse("multipart/form-data"), et_price.getText().toString());
                    RequestBody quantity = RequestBody.create(MediaType.parse("multipart/form-data"),et_quantity.getText().toString());
                    RequestBody supplier = RequestBody.create(MediaType.parse("multipart/form-data"),et_supplier.getText().toString());
                    RequestBody category = RequestBody.create(MediaType.parse("multipart/form-data"), et_category.getText().toString());
                    RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), et_category.getText().toString());
                    RequestBody trademark = RequestBody.create(MediaType.parse("multipart/form-data"), et_trademark.getText().toString());
                    RequestBody origin = RequestBody.create(MediaType.parse("multipart/form-data"), et_origin.getText().toString());
                    RequestBody sex = RequestBody.create(MediaType.parse("multipart/form-data"), et_sex.getText().toString());
                    RequestBody skinproblems = RequestBody.create(MediaType.parse("multipart/form-data"),et_skinproblems.getText().toString());

                    apiService =APIService.apiService;
                    Call<Void> call = apiService.updateAd(
                            id,
                            product_name,
                            price,
                            priceold,
                            quantity,
                            null,
                            supplier,
                            category,
                            description,
                            trademark,
                            origin,
                            sex,
                            skinproblems,
                            null,
                            null,
                            null,
                            null,
                            null,
                            imagePart
                    );

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(Edit_Product.this, "Product update successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Edit_Product.this, ProductManagerment.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Edit_Product.this, "Failed to update product", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Edit_Product.this, ProductManagerment.class);
                                startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(Edit_Product.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

        });


    }
    private void CheckPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else  {
            requestPermissions(permissions(), MY_REQUEST_CODE);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public  static String [] getStorge_permissions_33 = {
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO
    };
    public static  String[] storge_permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    public  static  String [] permissions() {
        String[] p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = getStorge_permissions_33;
        } else {
            p = storge_permissions;
        }
        return p;
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            iv_preview_image.setImageBitmap(bitmap);

                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private void AnhXa() {
        et_name=(EditText) findViewById(R.id.et_name);
        et_category=(EditText) findViewById(R.id.et_category);
        et_describe=(EditText) findViewById(R.id.et_describe);
        et_origin=(EditText) findViewById(R.id.et_origin);
        et_price=(EditText) findViewById(R.id.et_price);
        et_quantity=(EditText) findViewById(R.id.et_quantity);
        et_sex=(EditText) findViewById(R.id.et_sex);
        et_skinproblems=(EditText) findViewById(R.id.et_skinproblems);
        et_supplier=(EditText) findViewById(R.id.et_supplier);
        et_trademark=(EditText) findViewById(R.id.et_trademark);
        iv_preview_image=(ImageView) findViewById(R.id.iv_preview_image);
        btn_choose_image=(Button) findViewById(R.id.btn_choose_image);
        btn_add_product=(Button) findViewById(R.id.btn_add_product);

    }
}
package com.example.appfood.api;

import com.example.appfood.model.DoanhThu;
import com.example.appfood.model.ListMuaHuy;
import com.example.appfood.model.ListProductOrrder;
import com.example.appfood.model.LoginModel;
import com.example.appfood.model.MessagerProductAll;
import com.example.appfood.model.ProductModel;

import com.example.appfood.model.Products;
import com.example.appfood.model.UserAll;
import com.example.appfood.model.UserModel;
import com.example.appfood.model.category;
import com.example.appfood.model.listDoanhThu;
import com.example.appfood.model.listOrder;
import com.example.appfood.model.listcategory;
import com.example.appfood.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl(Constants.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("/products/all")
    Call<MessagerProductAll> getAllProducts(@Query("page") int page, @Query("page_size") int pageSize);

    @GET("/products/category")
    Call<listcategory> getCategories();





    @Multipart
    @POST("/products/insert")
    Call<Void> createProduct(
            @Part("name") RequestBody name,
            @Part("price") RequestBody price,
            @Part("quantity") RequestBody quantity,
            @Part("supplier") RequestBody supplier,
            @Part("category") RequestBody category,
            @Part("description") RequestBody description,
            @Part("trademark") RequestBody trademark,
            @Part("origin") RequestBody origin,
            @Part("sex") RequestBody sex,
            @Part("skinproblems") RequestBody skinproblems,
            @Part MultipartBody.Part image
    );

    @PUT("/orders/adminupdatestatus")
    Call<Void>updatestatus(@Query("order_number") String order_number,
                       @Query("status") String status);
    @Multipart
    @PUT("/products/adupdate")
    Call<Void> updateAd(@Part("id") RequestBody id,
                                @Part("product_name") RequestBody productName,
                                @Part("price") RequestBody price,
                                @Part("priceold") RequestBody priceOld,
                                @Part("quantity") RequestBody quantity,
                                @Part("sold") RequestBody sold,
                                @Part("supplier") RequestBody supplier,
                                @Part("category") RequestBody category,
                                @Part("description") RequestBody description,
                                @Part("trademark") RequestBody trademark,
                                @Part("origin") RequestBody origin,
                                @Part("sex") RequestBody sex,
                                @Part("skinproblems") RequestBody skinProblems,
                                @Part("addtocart") RequestBody addToCart,
                                @Part("addtofavorite") RequestBody addToFavorite,
                                @Part("share") RequestBody share,
                                @Part("rain") RequestBody rain,
                                @Part("active") RequestBody active,
                                @Part MultipartBody.Part image);




    @GET("/users/login")
    Call<LoginModel> login(@Query("email") String email, @Query("password") String password);



    @GET("/products/top10banchay")
    Call<Products>getTop10BanChay();
    @GET("/products/adAll")
    Call<Products>getAllProducts();

    @GET("/products/top10yeuthich")
    Call<Products>getTop10yeuThich();

    @GET("/products/top10chiase")
    Call<Products>getTop10ChiaSe();
    @GET("/products/top10danhgia")
    Call<Products>top10danhgia();

    @GET("/products/probycate")
    Call<Products> getProducts(@Query("category") String category);
    @GET("/users/")
    Call<UserAll> getAllUser(@Query("page") int page, @Query("page_size") int pageSize);

    @GET("/orders/getAdmin")
    Call<listOrder> getOrders();

    @GET("/orders/getProductsAdmin")
    Call<listOrder> getOrders(@Query("order_number") String order_number);

    @GET("/orders/thongkedoanhthuthang")
    Call<listDoanhThu> getdoanhthuthang(@Query("thang") int thang,@Query("nam") int nam);

    @GET("/orders/thongkedoanhthungay")
    Call<listDoanhThu> getdoanhthungay(@Query("ngay")int ngay,@Query("thang") int thang,@Query("nam") int nam);

    @GET("/orders/thongkedoanhthunam")
    Call<listDoanhThu> getdoanhthunam(@Query("nam") int nam);


    // thống kê số đơn hàng theo status
    @GET("/orders/CountStatus")
    Call<listDoanhThu> getCount(@Query("status") String status);


    @GET("/orders/thongkesodonhangthang")
    Call<listDoanhThu> getdonhangthang(@Query("thang") int thang,@Query("nam") int nam);

    @GET("/orders/thongkesodonhangngay")
    Call<listDoanhThu> getdonhangngay(@Query("ngay")int ngay,@Query("thang") int thang,@Query("nam") int nam);

    @GET("/orders/thongkesodonhangnam")
    Call<listDoanhThu> getdonhangnam(@Query("nam") int nam);

    @GET("/orders/thongkenguoidunghuydon")
    Call<ListMuaHuy> gettop10HuyDon();

    @GET("/orders/thongkenguoidung")
    Call<ListMuaHuy> getTopMua();





    //lấy số đơn hàng theo status

    @GET("/orders/thongkedonhangstatusthang")
    Call<listDoanhThu> getdonhangstatusthang(@Query("thang") int thang,@Query("nam") int nam,@Query("status") String status);

    @GET("/orders/thongkedonhangstatusngay")
    Call<listDoanhThu> getdonhangstatusngay(@Query("ngay")int ngay,@Query("thang") int thang,@Query("nam") int nam,@Query("status") String status);

    @GET("/orders/thongkedonhangstatusnam")
    Call<listDoanhThu> getdonhangstatusnam(@Query("nam") int nam,@Query("status") String status);


    //lấy sản phẩm đã mua theo ngày tháng năm
    @GET("/orders/thongkesanphamtheongaythangnam")
    Call<ListProductOrrder>getproductdamuatheongaythangnam(@Query("day") int day,
                                                           @Query("month") int month,
                                                           @Query("year")int year,
                                                           @Query("flag")int flag);









  /*  @GET("lastproduct.php")
    Call<List<ProductModel>> getProductAll();
     @GET("categories.php")
    Call<List<CategoryModel>> getCategoryAll();


    @POST("registrationapi.php?apicall=login")
    @FormUrlEncoded
    Call<MessageModel> login(@Field("username") String username,
                             @Field("password") String password);

    @POST("getcategory.php")
    @FormUrlEncoded
    Call<List<ProductModel>> getProductByCategoryId(@Field("idcategory") String idcategory);

    @Multipart
    @POST("updateimages.php")
    Call<List<ImageUpload>> upload(@Part("id") RequestBody id, @Part MultipartBody.Part images);

    @FormUrlEncoded
    @POST("newmealdetail.php")
    Call<MessModel> getProductDetail(@Field("id") String id);*/
}

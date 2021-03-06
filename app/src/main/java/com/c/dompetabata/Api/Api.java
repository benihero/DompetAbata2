package com.c.dompetabata.Api;

import com.c.dompetabata.Respon.Respon;
import com.c.dompetabata.Respon.ResponBanner;
import com.c.dompetabata.Respon.ResponEditKec;
import com.c.dompetabata.Respon.ResponEditLokasi;
import com.c.dompetabata.Respon.ResponEditPost;
import com.c.dompetabata.Respon.ResponEditkel;
import com.c.dompetabata.Respon.ResponK;
import com.c.dompetabata.Respon.ResponKEditKab;
import com.c.dompetabata.Respon.ResponKe;
import com.c.dompetabata.Respon.ResponMenu;
import com.c.dompetabata.Respon.ResponMenuUtama;
import com.c.dompetabata.Respon.ResponPost;
import com.c.dompetabata.Respon.ResponProfil;
import com.c.dompetabata.Respon.ResponSubCategory;
import com.c.dompetabata.Respon.Responkel;
import com.c.dompetabata.Model.MOtpVerif;
import com.c.dompetabata.Model.MRegisData;
import com.c.dompetabata.Model.MRegister;
import com.c.dompetabata.Model.Mlogin;
import com.c.dompetabata.Model.Mphone;
import com.c.dompetabata.Model.MsetPIN;
import com.c.dompetabata.Model.Responphoto;
import com.c.dompetabata.menuUtama.PaketData.PulsaPrabayar.ResponPulsaPra;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    @Headers("Content-Type: application/json")
    @POST("signin")
    Call<Mlogin> Login(@Body Mlogin mlogin);

    @Headers("Content-Type: application/json")
    @POST("signup")
    Call<MRegister> Register(@Body MRegister mRegister);

    @Headers("Content-Type: application/json")
    @POST("otp-email")
    Call<MRegisData> SendOTP(@Body MRegisData mRegisData);

    @Headers("Content-Type: application/json")
    @POST("otp-verify")
    Call<MOtpVerif> verifOTP(@Body MOtpVerif mOtpVerif);

    @Multipart
    @POST("ekyc-idcard")
    Call<Responphoto> uploadImage(@Part MultipartBody.Part image,@Part("id") RequestBody id);

    @Multipart
    @POST("ekyc-selfie")
    Call<Responphoto> uploadImageDiri(@Part MultipartBody.Part image,@Part("id") RequestBody id);

    @Multipart
    @POST("ekyc-idcardselfie")
    Call<Responphoto> uploadImageDiridanKTP(@Part MultipartBody.Part image,@Part("id") RequestBody id);

    @GET("all-province?next=39")
    Call<Respon> getAllProvinsi();

    @GET("profile")
    Call<Mlogin> getProfile(@Header("X-Signature")String token);

    @GET("banner/user/052f96d0-78a2-4df7-a9df-134484ca1446")
    Call<ResponBanner> getBanner(@Header("X-Signature")String token);

    @GET("profile")
    Call<ResponProfil> getProfileDas(@Header("X-Signature")String token);

    @GET("all-product-category")
    Call<ResponMenu> getAllProduct(@Header("X-Signature")String token);


    @POST("set-pin")
    Call<MsetPIN> SetPIN(@Header("X-Signature")String token, @Body MsetPIN msetPIN);

    @POST("auth-check")
    Call<Mphone> ChekPhone(@Body Mphone mphone);
    @GET("regencies/province/{id}")
    Call<ResponK> getAllKabupaten(@Path("id") long id);

    @GET("districts/regencies/{id}")
    Call<ResponKe> getAllKecamatan(@Path("id") long id);

    @Headers("Content-Type: application/json")
    @GET("province/{id}")
    Call<ResponEditLokasi> getProvinsiByIdd(@Path("id") long id);

    @GET("regencies/{id}")
    Call<ResponKEditKab> getKabupatenById(@Path("id") long id);

    @GET("product/sub-category/{id}")
    Call<ResponPulsaPra> getProdukPulsaPraById(@Header("X-Signature")String token,@Path("id") String id);

    @GET("districts/{id}")
    Call<ResponEditKec> getKecamatanById(@Path("id") long id);

    @GET("sub-districts/{id}")
    Call<ResponEditkel> getKelurahanById(@Path("id") long id);

    @GET("postal-code/{id}")
    Call<ResponEditPost> getPostById(@Path("id") long id);


    @GET("product-subcategory/prefix/{id}/{prefix}")
    Call<ResponSubCategory> getSubPrdoductByPrefix(@Header("X-Signature")String token,@Path("prefix") String prefix,@Path("id") String id);

    @GET("sub-districts/districts/{id}")
    Call<Responkel> getAllKelurahan(@Path("id") long id);

    @GET("postal-code/sub-districts/{id}")
    Call<ResponPost> getAllPost(@Path("id") long id);

    @GET("all-product-category?$limit=11&$order=urutan&status=1")
    Call<ResponMenuUtama> getAllMenu(@Header("X-Signature")String token);
    @GET("all-product-category?$limit=&$order=urutan&status=1")
    Call<ResponMenuUtama> getAllMenu2(@Header("X-Signature")String token);

}

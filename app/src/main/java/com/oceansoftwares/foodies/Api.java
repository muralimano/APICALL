package com.oceansoftwares.foodies;

import android.service.autofill.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Api {

    @POST("processRegistration")
    Call<UserData> processRegistration(@Field("customers_firstname") String customers_firstname,
                                       @Field("customers_lastname") String customers_lastname,
                                       @Field("customers_email_address") String customers_email_address,
                                       @Field("customers_password") String customers_password,
                                       @Field("customers_telephone") String customers_telephone,
                                       @Field("customers_picture") String customers_picture);
}

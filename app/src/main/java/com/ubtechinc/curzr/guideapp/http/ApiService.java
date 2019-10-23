package com.ubtechinc.curzr.guideapp.http;


import com.ubtechinc.curzr.guideapp.bean.CurrencyBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    String BASE_URL = "";

    //网络请求时长
    int HTTP_TIME =30;

    /***
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResponse<CurrencyBean.DataBean>> login(@Field("username") String username, @Field("password") String password);
}

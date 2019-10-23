package com.ubtechinc.curzr.guideapp.http;


import com.trello.rxlifecycle2.LifecycleTransformer;
import com.ubtechinc.curzr.guideapp.bean.CurrencyBean;
import com.ubtechinc.curzr.utils.MyLogger;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author duanjianlin
 * @description:
 * @date : 19/08/12 20:43
 */
public class RetrofitFactory {
    private final ApiService mApiService;

    private static RetrofitFactory mRetrofitFactory;

    private RetrofitFactory() {
        //创建日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设定日志级别
        //创建OkHttpClient
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(ApiService.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)//添加拦截器
                .build();
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava2转换器
                .client(mOkHttpClient)
                .build();

        //创建接口实现类
        mApiService = retrofit.create(ApiService.class);

    }

    public static RetrofitFactory getInstence() {
        if (mRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null)
                    mRetrofitFactory = new RetrofitFactory();
            }
        }
        return mRetrofitFactory;
    }

    public ApiService API() {
        return mApiService;
    }

    public ObservableTransformer threadTransformer(){
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    /**
     * 带生命感知的登录请求，如果登录过程中，界面退出，理解结束本次请求释放资源，防止内存泄漏。
     * @param username
     * @param password
     * @param lifecycleTransformer
     * @param scheduler
     */
    public void login(String username, String password, LifecycleTransformer<Long> lifecycleTransformer, BaseObserver<CurrencyBean.DataBean> scheduler) {
        API()
                .login(username,password)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        MyLogger.dLog().e("Unsubscribing subscription from login()");
                    }
                })
                .compose(threadTransformer())
                .compose(lifecycleTransformer)
                .subscribe(scheduler);
    }

}

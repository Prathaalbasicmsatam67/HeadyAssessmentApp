package com.pratham.headytest.di;

import com.google.gson.GsonBuilder;
import com.pratham.headytest.BuildConfig;
import com.pratham.headytest.rx.IoThreadScheduler;
import com.pratham.headytest.rx.SchedulerProvider;
import com.pratham.headytest.services.ApiService;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public abstract class NetworkModule {

    private static Interceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private static GsonConverterFactory provideGsonFactoryConvertor() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return GsonConverterFactory.create(gsonBuilder.setLenient().create());
    }


    private static Retrofit provideRetrofit(
            String baseUrl,
            Converter.Factory converterFactory,
            CallAdapter.Factory callAdapterFactory
    ) {

        OkHttpClient.Builder httpClient = new OkHttpClient
                .Builder();
        if (BuildConfig.DEBUG)
            httpClient.addInterceptor(getHttpLoggingInterceptor());
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(httpClient.build())
                .addCallAdapterFactory(callAdapterFactory);
        return builder.baseUrl(baseUrl).addConverterFactory(converterFactory).build();
    }

    @Provides
    static RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory(
            @IoThreadScheduler SchedulerProvider schedulerProvider) {
        return RxJava2CallAdapterFactory
                .createWithScheduler(schedulerProvider.provideSchedulerProvider());
    }

    @Provides
    static ApiService provideApiService(RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return provideRetrofit(BuildConfig.BASE_URL,
                provideGsonFactoryConvertor(),
                rxJava2CallAdapterFactory)
                .create(ApiService.class);
    }

}

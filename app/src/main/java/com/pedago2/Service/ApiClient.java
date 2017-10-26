package com.pedago2.Service;

/**
 * Created by firma on 26-Oct-17.
 */

public class ApiClient {
	
	 private static final String TAG = "ApiClient";

    public static Retrofit request(OnRequestTimeOut listener) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();
                    HttpUrl url = null;

                    HttpUrl.Builder builder = originalHttpUrl.newBuilder();

                    if (original.header("no_param") == null) {
                        builder.addQueryParameter("api_code", API_CODE);
                        builder.addQueryParameter("tipe_output", original.header("type_output"));
                        if (original.header("type_output").equals("6")) {
                            if (original.header("limit") != null)
                                builder.addQueryParameter("limit", original.header("limit"));
                            else
                                builder.addQueryParameter("limit", "0");
                        } else
                            builder.addQueryParameter("api_proses", "1");

                        if (original.header("No-Auth") != null) {
                            builder.addQueryParameter("get_user", "1");
                        } else {
                        }
                    }

                    url = builder.build();

                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();

                    try {
                        return chain.proceed(request);
                    } catch (SocketTimeoutException exception) {
                        exception.printStackTrace();
                    }

                    return chain.proceed(request);
                })

                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl("https://pedago.id/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
            }
}

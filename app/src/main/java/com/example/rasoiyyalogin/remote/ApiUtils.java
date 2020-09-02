package com.example.rasoiyyalogin.remote;

public class ApiUtils {
    public static final String BASE_URL = "https://gorest.co.in/public-api ";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}

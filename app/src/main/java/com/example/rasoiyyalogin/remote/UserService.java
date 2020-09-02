package com.example.rasoiyyalogin.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("{username}/{password}")
    Call login(@Path("username") String username, @Path("password") String password);
}

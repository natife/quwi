package com.natife.testapp.api;

import com.natife.testapp.model.RequestLogin;
import com.natife.testapp.model.ResponseLogin;
import com.natife.testapp.model.ResponseProject;
import com.natife.testapp.model.ResponseProjects;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("auth/login")
    Single<ResponseLogin> login(@Body RequestLogin requestLogin);

    @GET("projects-manage/index")
    Single<ResponseProjects> getProjects();

    @GET("projects-manage/{id}")
    Single<ResponseProject> getProject(@Path("id") int id);

    @FormUrlEncoded
    @POST("projects-manage/update")
    Single<ResponseProject> updateProjectName(@Query("id")int id, @Field("name") String name);
}

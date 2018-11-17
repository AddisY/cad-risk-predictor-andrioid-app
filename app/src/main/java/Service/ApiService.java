package Service;

import java.util.Map;

import models.AnalysisResult;
import models.Data;
import models.Doctor;
import models.OrdinaryUser;
import models.User;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Gurme L on 3/21/2018.
 */
public interface ApiService {

    @POST("api/signup/doctor")
    Call<Void> signupDoctor(@Body Doctor doctor);

    @POST("api/signup/user")
    Call<Void> signupOrdinaryUser(@Body OrdinaryUser user);

    @POST("api/analyze-data")
    Call<AnalysisResult> analyzeData(@Body Data data);


    @FormUrlEncoded
    @POST("api/api-token-auth/")
    Call<Object> login(@FieldMap Map<String, String> data);


}

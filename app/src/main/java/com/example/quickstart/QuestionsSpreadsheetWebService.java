package com.example.quickstart;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionsSpreadsheetWebService {

    @POST("1K4pYrnnpDD3qMnKHUztM-iIHrwgkoEBugGURQA1w_ik")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.177011303") String name,
            @Field("entry.1905894722") String email,
            @Field("entry.2030546518") String mobile,
            @Field("entry.953153417") String answerQuestionCat
    );
}

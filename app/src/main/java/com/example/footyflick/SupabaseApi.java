package com.example.footyflick;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import java.util.List;

public interface SupabaseApi {

    @Headers({
            "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJjb2N2eHVldmFrdGRud3dubGNrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTE0NTkxMzQsImV4cCI6MjA2NzAzNTEzNH0.fTFLUfhht9pu_9DvnRsfXv9sOEYndsRDiAEMCe6nk8A",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJjb2N2eHVldmFrdGRud3dubGNrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTE0NTkxMzQsImV4cCI6MjA2NzAzNTEzNH0.fTFLUfhht9pu_9DvnRsfXv9sOEYndsRDiAEMCe6nk8A"
    })
    @GET("rest/v1/users")
    Call<List<datamodel_user>> getUsers();
}

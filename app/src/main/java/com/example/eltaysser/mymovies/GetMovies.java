package com.example.eltaysser.mymovies;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class GetMovies {
    // i define some Attributes For receive some details From Json File
    private String title = "";
    private String desc = "";
    private String image = "";
    private String vote = "";
    private final Context context;

    public GetMovies(Context context) {
        this.context = context;
    }

    // Create This Method Take url as a parameter and connect
    // with the internet to fetch the request data from the server
    // and this return ArrayList<LayoutContent>.
    public void GetArray(String MyUrl, final VolleyCallBack volleyCallBack) {
        final ArrayList<LayoutContent> data = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, MyUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject parent = new JSONObject(response);
                    JSONArray results = parent.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject detailes = results.getJSONObject(i);
                        title = detailes.getString("title");
                        image = detailes.getString("poster_path");
                        desc = detailes.getString("overview");
                        vote = detailes.getString("vote_count");
                        data.add(new LayoutContent(image, title, desc, vote));
                    }
                    if (data.isEmpty()){
                        Toast.makeText(context,"Its Null"+data.size(),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Its not Null"+data.size(),Toast.LENGTH_SHORT).show();
                        //here implementation for interface class that used to call back the arraylist
                        volleyCallBack.onSuccessResponse(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }



public interface VolleyCallBack{
        void onSuccessResponse(ArrayList<LayoutContent> layoutContents);
}
}
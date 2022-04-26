package com.nareshit.recyclerview_book;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;
    private String result;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mRecyclerView=findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList=new ArrayList<>();
        
        mRequestQueue= Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url="https://www.googleapis.com/books/v1/volumes?q=android";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,
                url,null,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("items");
                            for(int i=0; i<items.length();i++){
                                JSONObject jsonObject = items.getJSONObject(i);
                                JSONObject vol = jsonObject.getJSONObject("volumeInfo");
                                String title = vol.getString("title");
                                JSONObject il = vol.getJSONObject("imageLinks");
                                String imageLink = il.getString("thumbnail");
                                JSONArray authors = vol.getJSONArray("authors");
                                String auth = "";
                                for(int j=0; j<authors.length()-1;j++){
                                    auth = auth+authors.getString(j)+",";
                                }
                                auth = auth+authors.getString(authors.length()-1);
                                ExampleItem ei = new ExampleItem(imageLink,title,auth);
                                mExampleList.add(ei);
                            }
                            ExampleAdapter ea = new ExampleAdapter(MainActivity.this,mExampleList);
                            mRecyclerView.setAdapter(ea);
                            mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        mRequestQueue.add(request);

    }
}
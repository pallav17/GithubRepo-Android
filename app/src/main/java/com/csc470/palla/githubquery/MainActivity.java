package com.csc470.palla.githubquery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csc470.palla.githubquery.utlis.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private int[] idArray;
    private String[] nameArray;
    private TextView resultTextVIew;
    private ProgressBar progressBar;
    private sampleAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pb_progress);
        resultTextVIew=  findViewById(R.id.tv_result);

       URL url =  NetworkUtils.buildURL(NetworkUtils.BASE_URL);
       new GithubGetRepo().execute(url);
      /*  recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new sampleAdapter(this);
        recyclerView.setAdapter(adapter);*/

}



class GithubGetRepo extends AsyncTask<URL,Void,String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
       resultTextVIew.setVisibility(View.INVISIBLE);
    }

    @Override
    protected String doInBackground(URL... urls) {
        String data = null;
        try {
           data = NetworkUtils.getResponse(urls[0]);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            JSONArray jsonArray = new JSONArray(data);
            int length = jsonArray.length();
            idArray = new int[length];
            nameArray = new String[length];
            for (int i = 0; i< length ; i++){
                JSONObject object = jsonArray.getJSONObject(i);
                idArray[i] = object.getInt("id");
                nameArray[i] = object.getString("name");

                JSONObject owner = object.getJSONObject("licenses");
                Log.w("Main", "Login is " + owner.getString("name"));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);
        progressBar.setVisibility(View.INVISIBLE);

        if(data != null){
            resultTextVIew.setVisibility(View.VISIBLE);
            resultTextVIew.setText("Id : " + idArray[0] + " Name is :" + nameArray[0]);
        }
    }
}
}

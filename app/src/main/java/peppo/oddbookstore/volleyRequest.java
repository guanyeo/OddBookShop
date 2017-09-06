package peppo.oddbookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**volleyRequest = Main
 * volleyCaller = Used for volley class to call for JSON *needs to be put on Application in Manifest
 * volleyData = For recycler view to access individual item
 * volleyAdapter = For recyclerView to store and access item temporarily (Bad analogy, i know)
 * volley_request = .xml main UI
 * volley_item = .xml individual item for recyclerView
 * **/

public class volleyRequest extends AppCompatActivity {
    private RecyclerView mVolleyRecycler;
    private volleyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_request);
        makeJSONArrayRequest();
    }

    private void makeJSONArrayRequest(){
       final List<volleyData> data = new ArrayList();
        JsonArrayRequest req = new JsonArrayRequest("http://198.211.112.145:8001/api/books", new Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject books = (JSONObject) response.get(i);
                        volleyData vd = new volleyData();
                        vd.bookName =  books.getString("name");
                        data.add(vd);
                    }
                    mVolleyRecycler = (RecyclerView)findViewById(R.id.volleyRecycler);
                    mAdapter = new volleyAdapter(volleyRequest.this, data);
                    mVolleyRecycler.setAdapter(mAdapter);
                    mVolleyRecycler.setLayoutManager(new LinearLayoutManager(volleyRequest.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        volleyCaller.getsInstance().addToRequestQueue(req);

    }
}
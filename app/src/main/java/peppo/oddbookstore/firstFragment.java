package peppo.oddbookstore;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 05/09/2017.
 */

public class firstFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    protected volleyAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.booksNameRecycler);
        final List<volleyData> data = new ArrayList();
        JsonArrayRequest req = new JsonArrayRequest("http://198.211.112.145:8001/api/books", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject books = (JSONObject) response.get(i);
                        volleyData vd = new volleyData();
                        vd.bookName =  books.getString("name");
                        data.add(vd);
                        Collections.sort(data, new Comparator<volleyData>() {
                            @Override
                            public int compare(volleyData o1, volleyData o2) {
                                return o1.bookName.compareToIgnoreCase(o2.bookName);
                            }
                        });
                    }
                    mRecyclerView = (RecyclerView)rootView.findViewById(R.id.booksNameRecycler);
                    mAdapter = new volleyAdapter(getActivity().getApplicationContext(), data);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        volleyCaller.getsInstance().addToRequestQueue(req);
        return rootView;
    }

}

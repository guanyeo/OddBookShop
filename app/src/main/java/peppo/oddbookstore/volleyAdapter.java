package peppo.oddbookstore;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 06/09/2017.
 */

public class volleyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<volleyData> data= Collections.emptyList();
    volleyData current;
    int currentPos=0;

    public volleyAdapter(Context context, List<volleyData> data) {
        this.context = context;
        inflater= LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //R.layout.item is Item to be pushed into recycler
        View view=inflater.inflate(R.layout.volley_item, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        volleyData current=data.get(position);
        myHolder.getTxtBookName().setText(current.bookName);
    }

    @Override
    public int getItemCount() {
        return data.size(); //Return total item from list
    }

    private  static class MyHolder extends RecyclerView.ViewHolder {
        private final TextView txtBookName;
        public MyHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  v.getContext().startActivity(new Intent(v.getContext(), OBSplash.class));
                }
            });
            txtBookName = (TextView)view.findViewById(R.id.item_book_name);
        }
        public TextView getTxtBookName(){return txtBookName;}
    }
}

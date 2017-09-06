package peppo.oddbookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void handleOnClick(View view) {
        switch(view.getId()){
            case R.id.register_link:
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent("peppo.oddbookstore.register");
                login.this.startActivity(mainIntent);
                login.this.finish();
                break;
            default:
                break;
        }
    }
}

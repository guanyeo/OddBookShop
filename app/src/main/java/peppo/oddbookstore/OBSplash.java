package peppo.oddbookstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class OBSplash extends Activity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obsplash2);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent("peppo.oddbookstore.main");
                OBSplash.this.startActivity(mainIntent);
                OBSplash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}


package com.sunil.materialtabtoolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.sunil.matrial.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by Luv_Gupta on 28-Jun-15.
 */
public class LoadingContentActivity extends Activity{

    private static final String TAG="LoadingContentActivity";
    private ProgressBar progressbar;

   public static ArrayList<UserInfo> list=new ArrayList<>();

    String imageurl[]={"http://www.nollywoodaccess.com/wp-content/uploads/2013/05/bollywood-aishwarya-rai-red-sari-without-clothes-2062753630.jpg",
    "http://cdn.koimoi.com/wp-content/new-galleries/2012/12/Will-Dabanng-2-be-Salman-s-Bollywood-s-or-2012-s-highest-Box-Office-Opener.jpg",
    "http://images.indiatvnews.com/entertainmentbollywood/kareena-kapoor-bollywood.jpg",
    "http://im.rediff.com/movies/2013/jul/09bollywood-vintage-mode8.jpg",
    "http://sifetbabo.com/wp-content/uploads/2014/07/priyanka-chopra-beautiful-bollywood-actress.jpg",
    "http://alltoptens.com/wp-content/uploads/2013/12/Top-Ten-Bollywood-actresses-of-2014.jpg",
    "http://punnobhumi.com/files/uploads/2014/12/Bollywood-Actress-82.jpg",
    "http://images6.fanpop.com/image/photos/33700000/Ileana-bollywood-33771090-700-878.jpg"
    };
    String names[]={"Aishwarya",
            "Salman",
            "Kareena",
            "Deepika",
            "Priyanka",
            "Anuska",
            "Sonakshi",
            "Eliyana"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadcontentscreen);

        progressbar=(ProgressBar) findViewById(R.id.progressBar2);

        list.clear();
        for (int i=0; i < imageurl.length; i++){
            UserInfo infomodel=new UserInfo();
            infomodel.setName(names[i]);
            infomodel.setUrl(imageurl[i]);
            list.add(infomodel);
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent=new Intent(LoadingContentActivity.this, MainActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                intent.putParcelableArrayListExtra("list", list);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}

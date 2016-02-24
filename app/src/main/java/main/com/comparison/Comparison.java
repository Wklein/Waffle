package main.com.comparison;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Comparison{
    private String imagePath1;
    private String imagePath2;
    private int votes1;
    private int votes2;

//    TODO: There are probably several attributes that makes sense to include once these are tied to user accounts.
//          Think on it...
//    private String owner;
//    private Date createdOn;

    public Comparison(String i1, String i2){
        imagePath1 = i1;
        imagePath2 = i2;
        votes1 = 0;
        votes2 = 0;
    }

    public Drawable getImage(int i) throws IOException{
        if(i == 1)
            return drawableFromUrl(imagePath1);
        else
            return drawableFromUrl(imagePath2);
    }

    public int getOptionVotes(int i){
        if(i == 1)
            return votes1;
        else
            return votes2;
    }

    public void vote(int i){
        if(i == 1)
            votes1++;
        else
            votes2++;
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }
}
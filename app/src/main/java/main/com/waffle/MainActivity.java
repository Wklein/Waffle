package main.com.waffle;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.com.comparison.Comparison;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Comparison> comparisonList;
    Comparison currentComparison;

    private Drawable d1;
    private Drawable d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comparisonList = new ArrayList<Comparison>();
        System.out.println(System.getProperty("user.dir") + " This is the current file path\n\n\n\n\n\n");

        for(int i = 0; i < 10; i ++){
            comparisonList.add(new Comparison("http://lorempixel.com/400/200/",
                    "http://lorempixel.com/400/200/"));
        }

        currentComparison = comparisonList.get(0);
        new PrepNextComparison().execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    public void imageClick(View v) throws IOException{
        switch (v.getId()) {
            case (R.id.imageView1):
                processClick(1);
                break;
            case (R.id.imageView2):
                processClick(2);
                break;
        }
        new PrepNextComparison().execute("");
    }

    public void processClick(int i) throws IOException{
        if(i == 1 || i == 2)
            currentComparison.vote(i);

        int tmpSel = (int)(Math.random() * 10);

        TextView tv = (TextView)findViewById(R.id.text1);
        tv.setText(String.valueOf(currentComparison.getOptionVotes(1)));
        tv = (TextView)findViewById(R.id.text2);
        tv.setText(String.valueOf(currentComparison.getOptionVotes(2)));

        currentComparison = comparisonList.get(tmpSel);

    }

    public void setNext(){

    }

    private class PrepNextComparison extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                d1 = currentComparison.getImage(1);
                d2 = currentComparison.getImage(2);
            }catch(IOException e){
                e.printStackTrace();
            }
            return "executed";
        }

        @Override
        protected void onPostExecute(String result) {
            ImageButton button =(ImageButton)findViewById(R.id.imageView1);
            button.setImageDrawable(d1);
            button =(ImageButton)findViewById(R.id.imageView2);
            button.setImageDrawable(d2);

            TextView tv = (TextView)findViewById(R.id.text1);
            tv.setText(String.valueOf(currentComparison.getOptionVotes(1)));
            tv = (TextView)findViewById(R.id.text2);
            tv.setText(String.valueOf(currentComparison.getOptionVotes(2)));
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }


    public static void main(String[] args){
        List<Comparison> comparisonList = new ArrayList<Comparison>();

        comparisonList.add(new Comparison("res\\images\\0000" + (int)(Math.random() * 8 + 1) + ".jpg",
                "res\\images\\0000" + (int)(Math.random() * 8 + 1) + ".jpg"));
    }
}

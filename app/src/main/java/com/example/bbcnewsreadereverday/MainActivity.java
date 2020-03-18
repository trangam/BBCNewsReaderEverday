package com.example.bbcnewsreadereverday;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    HttpURLConnection urlConnection;
    List<BBCNews> bbcNews;
    ListView listView;
    TextView thistitle;
    ProgressBar progressBar;
   // BBCNewsAdapter adapter;
 //   private BBCNewsAdapter adapter;
    public static final String ITEM_TITLE = "Title";
    public static final String ITEM_LINK = "Link";
    public static final String ITEM_DESCRIPTION = "Description";
    public static final String ITEM_DATE = "Date";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.bbcList);

    //    adapter=new BBCNewsAdapter(this, this.bbcNews);
        ArrayList<String> headlines = new ArrayList<>();
BBCAsyncTask getxml= new BBCAsyncTask();
getxml.execute();
headlines=getxml.heads();
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, headlines);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) ->
        {
            BBCNews bbcItem = (BBCNews) parent.getAdapter().getItem(position);
            Bundle dataToPass = new Bundle();
            dataToPass.putString(ITEM_TITLE,bbcItem.getTitle() );
            dataToPass.putString(ITEM_DESCRIPTION, bbcItem.getData());
            dataToPass.putString(ITEM_DATE, bbcItem.getDate());
            dataToPass.putString(ITEM_LINK, bbcItem.getLink());

//            Intent nextActivity = new Intent(this, EmptyActivity.class);
//            nextActivity.putExtras(dataToPass); //send data to next activity
//            startActivity(nextActivity); //make the transition
        });

    }
}





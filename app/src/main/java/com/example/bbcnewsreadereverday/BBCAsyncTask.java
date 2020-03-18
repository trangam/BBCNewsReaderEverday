package com.example.bbcnewsreadereverday;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BBCAsyncTask extends AsyncTask<Object,Integer,Object>{
    HttpURLConnection connection;
    static final String BBC_START_TAG="item";
    static final String BBC_TITLE_TAG="title";
    static final String BBC_DESCRIPTION_TAG="description";
    static final String BBC_LINK_TAG="link";
    static final String BBC_DATE_TAG="pubDate";
    ArrayList<BBCNews> bbcList=new ArrayList<>();
    ArrayList<String> headlines = new ArrayList();
    private Context ctx;
    private ProgressBar progressBar;
    private View container;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
//    public BBCAsyncTask(Context ctx, ProgressBar progressBar, View container) {
//        this.ctx = ctx;
//        this.progressBar = progressBar;
//        this.container = container;
//    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml");
            connection = (HttpURLConnection) url.openConnection();
            InputStream response = connection.getInputStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(response  , "UTF-8");

            BBCNews bbcNews = null;
            String curText = "";
            int eventType = xpp.getEventType();

//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                // Get the current tag
//                String tagname = xpp.getName();
//
//                // React to different event types appropriately
//                switch (eventType) {
//                    case XmlPullParser.START_TAG:
//                        if (tagname.equalsIgnoreCase(BBC_START_TAG)) {
//                            bbcNews = new BBCNews();
//                        }
//                        break;
//
//                    case XmlPullParser.TEXT:
//                        curText = xpp.getText();
//                        break;
//
//                    case XmlPullParser.END_TAG:
//                        if (tagname.equalsIgnoreCase(BBC_START_TAG)) {
//                            bbcList.add(bbcNews);
//                        } else if (tagname.equalsIgnoreCase(BBC_TITLE_TAG)) {
//                            headlines.add(curText);
//                          //  bbcNews.setTitle(curText);
//                        } else if (tagname.equalsIgnoreCase(BBC_DESCRIPTION_TAG)) {
//                            bbcNews.setData(curText);
//                        } else if (tagname.equalsIgnoreCase(BBC_DATE_TAG)) {
//                            bbcNews.setDate(curText);
//                        } else if (tagname.equalsIgnoreCase(BBC_LINK_TAG)) {
//                            bbcNews.setLink(curText);
//                        }
//                        break;
//
//                    default:
//                        break;
//                }
//                //move on to next iteration
//                eventType = xpp.next();
            boolean insideItem = false;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem)
                            headlines.add(xpp.nextText());
                    }
//                    } else if (xpp.getName().equalsIgnoreCase("link")) {
//                        if (insideItem)
//                            links.add(xpp.nextText()); //extract the link of article
//                    }
                } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    insideItem = false;
                }

                eventType = xpp.next(); //move to next element
            }


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
return headlines;
    }
    public void onPostExecute(String fromDoInBackground)
    {
        Intent intent = new Intent(ctx, MainActivity.class);
        ctx.startActivity(intent);


}
    public ArrayList<String> heads()
    {
        return headlines;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }
}
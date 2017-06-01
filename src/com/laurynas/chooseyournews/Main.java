package com.laurynas.chooseyournews;

/**
 * Created by Laurynas on 2017-05-26.
 */
import javafx.application.Application;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Example program to list links from a URL.
 */
public class Main{
    public static Headline[] main() throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.delfi.lt/sportas/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String HTML = getContent(doc , "container-col-left").html();
        String[] parts = HTML.split("<div class=\"content-new tb-show-publishtime\">");
        List<Headline> headlines = new ArrayList<>();
        for(String s : parts){
            List<String> bigNews = getAllThingsBetween("<a class=\"article-title\" href=\"", "</a>", s);
            for(String news : bigNews){
                String[] parts1 = news.split("\">");
                String headline = parts1[1];
                String link = parts1[0];
                String tema = link.split("/")[4];
                if(!headlines.contains(new Headline(headline, link, tema)))headlines.add(new Headline(headline, link, tema));
            }
        }
        List<Headline> removables = new ArrayList<>();
        for(int i = 0;i < headlines.size();i++){
            Headline current = headlines.get(i);
            int a = 0;
            for(Headline h : headlines){
                if(h.getHeadline().equals(current.getHeadline()) && headlines.indexOf(h) != headlines.indexOf(current)){
                    removables.add(current);
                }
            }
            System.out.println(a);
        }
        for(Headline r : removables)headlines.remove(r);
        Headline[] headlinesArray = headlines.toArray(new Headline[headlines.size()]);
        for(int i = 0;i < headlinesArray.length-1;i++){
            if(headlinesArray[i].getTema().compareTo(headlinesArray[i+1].getTema()) < 0 ){
                Headline temp = headlinesArray[i];
                headlinesArray[i] = headlinesArray[i + 1];
                headlinesArray[i + 1] = temp;
                i = 0;
            }
        }
        return headlinesArray;
    }

    public static Elements getContent(Document d, String div) {
        return d.select("div." + div);
    }
    private static List<String> getAllThingsBetween(String pat1, String pat2, String data){
        List<String> stringList = new ArrayList<String>();
        String regexString = Pattern.quote(pat1) + "(.*?)" + Pattern.quote(pat2);
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String textInBetween = matcher.group(1);
            stringList.add(textInBetween);
        }
        return stringList;
    }

}


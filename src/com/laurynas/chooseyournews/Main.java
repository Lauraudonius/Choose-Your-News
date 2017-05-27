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

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Example program to list links from a URL.
 */
public class Main{
    public static void main(String[] args) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.delfi.lt/sportas/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String HTML = getContent(doc , "container-col-left").html();
        List<String> allNames = getAllThingsBetween("\">", "</a>&nbsp;", HTML);
        for(String s : allNames){
            System.out.println(s);
        }
        List<String> Topics = getAllThingsBetween("<a class=\"category-header-link\" href=\"", "</a>", HTML);
        for(String s : Topics){
            s = s.split("\">")[1];
            System.out.println(s);
        }
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


package com.laibin.util;

import com.laibin.po.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {
//    public static void main(String[] args) throws IOException {
//        new HtmlParseUtil().parseJD("java").forEach(System.out::println);
//    }

    public List<Content> parseJD(String keyword) throws IOException {
        String url = String.format("https://search.jd.com/Search?keyword=%s&enc=utf-8", keyword);
        // 解析网页 jsoup 返回document
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        // 获取所有的li元素
        Elements elements = element.getElementsByTag("li");
        ArrayList<Content> goodsList = new ArrayList<>();
        // 获取元素的内容
        for (Element el : elements) {
            // 懒加载图片 source-data-lazy-img
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            goodsList.add(new Content(title, img, price));
        }
        return goodsList;
    }
}

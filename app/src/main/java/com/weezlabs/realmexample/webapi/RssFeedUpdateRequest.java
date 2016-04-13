package com.weezlabs.realmexample.webapi;

import android.app.IntentService;
import android.content.Intent;

import com.weezlabs.realmexample.models.RssRealmModel;
import com.weezlabs.realmexample.repositories.RssRepository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class RssFeedUpdateRequest extends IntentService {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    public RssFeedUpdateRequest() {
        super("rssUpdateRequest");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlString = intent.getDataString();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(urlString);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            List<RssRealmModel> rssList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    String title = getValueByTag("title", e);
                    String text = getValueByTag("description", e);
                    String link = getValueByTag("link", e);
                    String date = getValueByTag("pubDate", e);

                    RssRealmModel rss = new RssRealmModel();
                    rss.setTitle(title);
                    rss.setLink(link);
                    rss.setText(text);
                    rss.setDate(dateFormat.parse(date).getTime());

                    rssList.add(rss);
                }
            }

            RssRepository.updateFeedInChannel(rssList, urlString);
        } catch (IOException | ParserConfigurationException | ParseException | SAXException e) {
            e.printStackTrace();
        }
    }

    private String getValueByTag(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}

package com.bootcamp.bc.bc_yahoo_finance.infra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class YahooAccess {

    private final static Logger log = LoggerFactory.getLogger(YahooAccess.class);

    public static String crumb = null;
    public static String cookie = null;

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36 Edg/113.0.1774.42";
    public static final String USER_AGENT_MY_MACBOOK = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36";

    private static void setCookie() {
        try {
            URL url = new URI("https://fc.yahoo.com").toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            cookie = connection.getHeaderField("Set-Cookie");
            connection.disconnect();
        } catch (Exception e) {
            log.debug("Failed to set cookie from http request. Intraday quote requests will most likely fail.", e);
        }
    }

    private static void setCrumb() {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URI("https://query1.finance.yahoo.com/v1/test/getcrumb").toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Cookie", cookie);
            connection.addRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (Exception e) {
            log.debug("Failed to set crumb from http request. Intraday quote requests will most likely fail.", e);
        }
        crumb = response.toString();
    }

    public static synchronized void resetCookieCrumb() {
        setCookie();
        setCrumb();
    }

    public static synchronized String getCookie() {
        if (cookie == null || cookie.isEmpty()) {
            resetCookieCrumb();
        }
        return cookie;
    }

    public static synchronized String getCrumb() {
        if (crumb == null || crumb.isBlank()) {
            resetCookieCrumb();
        }
        return crumb;

    }

}
package com.self.cloud.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author heyong
 * @date 2020/3/25
 */
public class UrlSplitUtils {

    private static final String URL_SPLIT_PRE = "//";
    private static final String CHAR_COLON = ":";
    private static final String URL_SPLIT = "/";
    private static final String CHAR_REQUEST = "?";
    private static final String CHAR_DOLLAR = "$";
    private static final String CHAR_CONNECT = "&";
    private static final String CHAR_EQUST = "=";
    private static final String URL_PATTERN = "^((http|https)://)(?:(?:\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(?:\\d|[1-9]\\d|2[0-4]\\d|25[0-5])($|/|:\\d{1,4})";
    private static final String URL_NO_PORT_PATTERN = "^((http|https)://)(?:(?:\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(?:\\d|[1-9]\\d|2[0-4]\\d|25[0-5])";


    public static boolean isIPLegal(String url) {
        if (url == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(URL_PATTERN);
        return pattern.matcher(url).find();
    }

    public static String addDefPort(String url) {
        if (url == null) {
            return null;
        }
        url = url.trim();
        Pattern pattern1 = Pattern.compile(URL_NO_PORT_PATTERN + CHAR_DOLLAR);
        Pattern pattern2 = Pattern.compile(URL_NO_PORT_PATTERN + URL_SPLIT);
        if (pattern1.matcher(url).find()) {
            url += ":80";
        } else if (pattern2.matcher(url).find()) {
            String urlStart = url.substring(0, url.indexOf(CHAR_COLON + URL_SPLIT_PRE) + 3);
            String temp = url.substring(url.indexOf(CHAR_COLON + URL_SPLIT_PRE) + 3);
            StringBuffer strB = new StringBuffer(temp);
            strB.insert(temp.indexOf(URL_SPLIT), ":80");
            url = (urlStart + strB.toString());
        }
        return url;
    }

    public static String getIp(String url) {
        if (isIPLegal(url)) {
            url = addDefPort(url);
            String temp = url.substring(url.indexOf(CHAR_COLON + URL_SPLIT_PRE) + 3);
            return temp.substring(0, temp.indexOf(CHAR_COLON));
        }
        return null;
    }

    public static String getPort(String url) {
        if (isIPLegal(url)) {
            url = addDefPort(url);
            String temp = url.substring(url.indexOf(CHAR_COLON + URL_SPLIT_PRE) + 3);
            temp = temp.substring(temp.indexOf(CHAR_COLON) + 1).replace(" +", "");
            char[] chars = temp.toCharArray();
            String pr = "";
            for (char c : chars) {
                if (!String.valueOf(c).matches("[0-9]")) {
                    break;
                }
                pr += c;
            }
            return pr;
        }
        return null;
    }

    public static String getBasePath(String url) {
        if (isIPLegal(url)) {
            url = addDefPort(url);
            String temp = url.substring(url.indexOf(CHAR_COLON + URL_SPLIT_PRE) + 3);
            if (temp.contains(CHAR_REQUEST)) {
                temp = temp.split("\\" + CHAR_REQUEST)[0].replace(" +", "").replaceAll("/+", URL_SPLIT);
            }
            if (temp.contains(URL_SPLIT)) {
                temp = temp.substring(temp.indexOf(URL_SPLIT) + 1);
                if (temp.contains(URL_SPLIT)) {
                    return temp.substring(0, temp.indexOf(URL_SPLIT));
                } else {
                    return temp;
                }
            }
        }
        return null;
    }

    public static String getLastPath(String url) {
        if (isIPLegal(url)) {
            String temp = url.substring(url.indexOf(CHAR_COLON + URL_SPLIT_PRE) + 3);
            if (temp.contains(CHAR_REQUEST)) {
                temp = temp.split("\\" + CHAR_REQUEST)[0].replace(" +", "").replaceAll("/+", "/");
            }
            String[] str = temp.split(URL_SPLIT);
            return str[str.length - 1];
        }
        return null;
    }

    public static String getValue(String url, String name) {

        if (url == null) {
            return null;
        }
        String[] urlParts = url.split("\\" + CHAR_REQUEST);
        //没有参数
        if (urlParts.length == 1) {
            return null;
        }
        //有参数
        String[] params = urlParts[1].split(CHAR_CONNECT);

        for (int i = params.length; i > 0; i--) {
            if (params[i - 1] == null || params[i - 1] == "") {
                continue;
            }
            if (params[i - 1].matches(name + "=.*")) {
                return params[i - 1].split("=")[1];
            }
        }
        return null;
    }

    public static String setValue(String url, String name, String value) {

        if (StringUtils.isBlank(url) || StringUtils.isBlank(name)) {
            return null;
        }
        String[] urlParts = url.split("\\?");
        String baseUrl = urlParts[0].replaceAll(" +", "").replaceAll("/+", "/");
        //没有参数
        if (urlParts.length == 1) {
            return baseUrl + CHAR_REQUEST + name + CHAR_EQUST + value;
        }
        //有参数
        String[] params = urlParts[1].split(CHAR_CONNECT);
        Map<String, String> paramsmap = new HashMap<>();
        for (String p : params) {
            if (StringUtils.isNotBlank(p) && p.matches(".*=.*")) {
                String[] strs = p.split(CHAR_EQUST);
                paramsmap.put(strs[0], strs[1]);
            }
        }
        paramsmap.put(name, value);
        Iterator<Map.Entry<String, String>> entries = paramsmap.entrySet().iterator();
        String k, v;
        Boolean firstParam = true;
        Map.Entry<String, String> entry;
        while (entries.hasNext()) {
            entry = entries.next();
            k = entry.getKey();
            v = entry.getValue();
            if (firstParam) {
                firstParam = false;
                baseUrl += CHAR_REQUEST + k + CHAR_EQUST + v;
            } else {
                baseUrl += CHAR_CONNECT + k + CHAR_EQUST + v;
            }
        }
        return baseUrl;
    }


    public static void main(String[] args) {

//        String u1 = "https://172.254.8.204";
//        String u2 = "https://172.254.8.204/";
//        String u3 = "https://172.254.8.204/test?ty=8??ty=8";
//        String u4 = "https://172.254.8.204//test?ty=8??ty=8";
//        System.out.println(u1 + "==>" + UrlSplitUtils.isIPLegal(u1));
//        System.out.println(u2 + "==>" + UrlSplitUtils.isIPLegal(u2));
//        System.out.println(u3 + "==>" + UrlSplitUtils.isIPLegal(u3));
//        System.out.println(u4 + "==>" + UrlSplitUtils.isIPLegal(u4));
//        System.out.println(u1 + "==>" + UrlSplitUtils.addDefPort(u1));
//        System.out.println(u2 + "==>" + UrlSplitUtils.addDefPort(u2));
//        System.out.println(u3 + "==>" + UrlSplitUtils.addDefPort(u3));
//        System.out.println(u4 + "==>" + UrlSplitUtils.addDefPort(u4));
        String u1 = "https://172.254.8.204";
        String u2 = "https://172.254.8.204/";
        String u3 = "https://172.254.8.204/test?ty=8??ty=8";
        String u4 = "http://172.39.8.204///test";
        System.out.println(u1 + "==>" + UrlSplitUtils.isIPLegal(u1));
        System.out.println(u2 + "==>" + UrlSplitUtils.isIPLegal(u2));
        System.out.println(u3 + "==>" + UrlSplitUtils.isIPLegal(u3));
        System.out.println(u4 + "==>" + UrlSplitUtils.isIPLegal(u4));
        System.out.println("ip=" + UrlSplitUtils.getIp(u4) +
                ",port=" + UrlSplitUtils.getPort(u4) +
                ",basePath =" + UrlSplitUtils.getBasePath(u4) +
                ",lastPath =" + UrlSplitUtils.getLastPath(u4));
        System.out.println("paream[ty]=" + UrlSplitUtils.getValue(u4, "ty"));
        System.out.println("paream[ty]=" + UrlSplitUtils.setValue(u4, "ty", "ty56"));
    }
}

package com.xyb.util;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static String addWebUriToReq(HttpServletRequest req, String uriName){
        String contextPath = req.getContextPath();
        contextPath = contextPath.startsWith("//") ? contextPath.substring(1) : contextPath;
        String webUri = "http://" + req.getRemoteHost() + ":" + req.getServerPort() + contextPath + "/";
        req.setAttribute(uriName, webUri);
        return webUri;
    }

}

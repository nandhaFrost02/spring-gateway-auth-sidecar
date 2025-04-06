package com.sidecar.gateway.security;

public class Utilities {

    public static String getAuthSegment(String uri){
        String[] uriPathSplit = uri.split("/",3);
        return uriPathSplit.length <=1?"": uriPathSplit[1];
    }
}

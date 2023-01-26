package com.example.BankManagement.util;

public class SecurityConstants {

    /*
     * Secret key known by the backend application ONLY.
     * We set it like this for testing purposes.
     * 
     * In production environnment, the devops teams should
     * inject this value as a variable environnment with Github or Jenkins.
     */
    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

    /*
     * 
     */
    public static final String JWT_HEADER = "Authorization";
}
package com.cici.utils;

import com.cici.oauth.domain.AccountType;
import com.cici.util.SecurityUtils;

public class SecurityHelperUtils {


    private final static String ADMIN_CLIENT = "admin-client";
    private final static String APP_CLIENT = "app-client";

    public static AccountType getWalletAccountType(){
        String client = SecurityUtils.getCurrentClientId();
        if(APP_CLIENT.equals(client)){
            return AccountType.STORE;
        }else{
            return AccountType.ADMIN;
        }
    }
}

package com.user.service.util;

import com.github.ksuid.Ksuid;

public class UUID {
    private static volatile UUID uuid =null;
    private UUID(){}
    public static UUID getUuid(){
        if(uuid == null){
            synchronized (UUID.class){
                uuid = new UUID();
            }
        }
        return uuid;
    }
    public String getNewId(){
        return Ksuid.newKsuid().toString();
    }
}

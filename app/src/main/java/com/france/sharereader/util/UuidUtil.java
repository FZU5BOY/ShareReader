package com.france.sharereader.util;

import java.util.UUID;

/**
 * 对象的全球唯一标识
 * Created by Administrator on 2015/10/29.
 */
public  class UuidUtil {
    public static  String getUUID(){
        return UUID.randomUUID().toString();
    }
}

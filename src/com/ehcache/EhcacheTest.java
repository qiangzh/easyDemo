package com.ehcache;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 *  EhcacheTest  
 *  @author zhqiang
 *  @created 2013-12-3 ÉÏÎç10:03:57
 *  @lastModified       
 *  @history           
 */

public class EhcacheTest {

    public static void main(String[] args) {
        String cacheName = "MyCache";

        // CacheManager manager = CacheManager.getInstance();
        URL url = EhcacheTest.class.getClassLoader().getResource("com/ehcache/ehcache.xml");
        CacheManager manager = CacheManager.create(url);
        Cache cache = manager.getCache(cacheName);

        for (int i = 0; i < 10; i++) {
            Element element = new Element("key" + i, String.valueOf(i * 10));
            cache.put(element);
        }

        for (int i = 0; i < 10; i++) {
            Element element = cache.get("key" + i);
            if (element != null) {
                String str = (String) element.getObjectValue();
                System.out.println(str);
            }
        }

    }

}

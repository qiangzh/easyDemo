package com.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ComparatorTest {

    public static void main(String[] args) throws Exception {
        String str = "aasfffeevv@fff34";

        // Í³¼Æ        
        Map<Character, Integer> map = new HashMap();
        for (int i = 0, j = str.length(); i < j; i++) {
            int count = 0;
            if (map.containsKey(str.charAt(i))) {
                count = (Integer) map.get(str.charAt(i));
            }
            count++;
            map.put(str.charAt(i), count);
        }

        // ÅÅÐò
        List list = new ArrayList();
        Set<Entry<Character, Integer>> set = map.entrySet();
        for (Iterator iter = set.iterator(); iter.hasNext();) {
            Entry entry = (Entry) iter.next();
            list.add(entry);
        }
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object obj1, Object obj2) {
                Entry entry1 = (Entry) obj1;
                Entry entry2 = (Entry) obj2;
                if (entry1.getValue() == entry2.getValue()) {
                    return ((Character) entry1.getKey()).compareTo((Character) entry2.getKey());
                }
                else {
                    return ((Integer) entry2.getValue()).compareTo((Integer) entry1.getValue());
                }
            }
        });

        // Êä³ö
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Entry entry = (Entry) iter.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

}

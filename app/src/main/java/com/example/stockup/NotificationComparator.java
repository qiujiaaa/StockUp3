package com.example.stockup;

import java.util.Comparator;

public class NotificationComparator implements Comparator<Notification> {

    @Override
    public int compare(Notification o1, Notification o2) {

        String[] noti1 = o1.getTitle().split(" ");
        String[] noti2 = o2.getTitle().split(" ");

        if (noti1[1].equals(noti2[1])) { // same order number
            return noti2[4].compareTo(noti1[4]);
        } else { // diff order number
            return Integer.valueOf(noti2[1]) - Integer.valueOf(noti1[1]);
        }
    }
}

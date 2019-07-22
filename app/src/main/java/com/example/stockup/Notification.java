package com.example.stockup;

public class Notification {
    String title;
    String detail;
    boolean isRead;


    public Notification () { }

    public Notification(String notificationTitle, String notificationDetail, boolean notificationRead) {
        this.title = notificationTitle;
        this.detail = notificationDetail;
        this.isRead = notificationRead;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public boolean isRead() {
        return isRead;
    }

    public void update() {
        isRead = true;
    }
}

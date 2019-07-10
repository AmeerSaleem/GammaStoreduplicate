package com.example.gammastoreduplicate;

public class NotificationClass {

    String notification_source;
    String notification_message;
    String notification_image;

    public NotificationClass() {
    }

    public NotificationClass(String notification_source, String notification_message, String notification_image) {
        this.notification_source = notification_source;
        this.notification_message = notification_message;
        this.notification_image = notification_image;
    }
}

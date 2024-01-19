package com.example.myjournallapp2.model;

import java.io.Serializable;

public class JournalEntry implements Serializable {
    private String title;
    private String content;
    private String dateTime; // Adăugat pentru data și ora

    // Constructor actualizat pentru a include data și ora
    public JournalEntry(String title, String content, String dateTime) {
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
    }

    // Getteri și setteri pentru toate câmpurile
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return title + " (" + dateTime + ")"; // Modificat pentru a include data și ora
    }
}

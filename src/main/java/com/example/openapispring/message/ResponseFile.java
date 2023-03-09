package com.example.openapispring.message;

public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
    private long time;

    public ResponseFile() {
    }

    public ResponseFile(String name, String url, String type, long size, long time) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.time = time;
    }
}

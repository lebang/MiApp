package com.miapp.Model;

/**
 * Created by lebang on 16-7-27.
 */
public class TopStory {
    public String title;
    public String id;
    public String image;

    public TopStory(String id, String title, String image) {
        this.title = title;
        this.id = id;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

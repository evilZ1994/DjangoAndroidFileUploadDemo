package me.codekiller.djangofiledemo.bean;

public class ImageUpResult {
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ImageUpResult{" +
                "response='" + response + '\'' +
                '}';
    }
}

package se.dullestwall.dietapp;

public class Diet {
    private long id;
    private String name;
    private String imageID;

    public Diet(long id, String name, String imageID) {
        this.id = id;
        this.name = name;
        this.imageID = imageID;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageID() {
        return imageID;
    }
}

package emoji;

public class Emoji {
    private String name;
    private String imageUrl;
    private String localPath;
    private String skinTone;

    public Emoji() {}

    public Emoji(String name, String imageUrl, String skinTone) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.skinTone = skinTone;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getLocalPath() { return localPath; }
    public void setLocalPath(String localPath) { this.localPath = localPath; }

    public String getSkinTone() { return skinTone; }
    public void setSkinTone(String skinTone) { this.skinTone = skinTone; }
} 
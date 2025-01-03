package emoji;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class EmojiDownloader {
    public void downloadEmojiImages(List<Emoji> emojis, String destinationFolder) {
        for (Emoji emoji : emojis) {
            try (InputStream in = new URL(emoji.getImageUrl()).openStream()) {
                Path path = Paths.get(destinationFolder, sanitizeFileName(emoji.getName()) + ".png");
                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
                emoji.setLocalPath(path.toString());
            } catch (IOException e) {
                System.err.println("Failed to download image for emoji: " + emoji.getName() + ", " + e.getMessage());
            }
        }
    }

    private String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9\\-_]", "_");
    }
} 
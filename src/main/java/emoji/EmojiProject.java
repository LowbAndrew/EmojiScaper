package emoji;
import java.util.List;
import java.io.File;
public class EmojiProject {
    public static void main(String[] args) {
        try {
            EmojiScraper scraper = new EmojiScraper();
            List<Emoji> emojis = scraper.scrapeAllEmojis();

            if (emojis == null || emojis.isEmpty()) {
                System.out.println("警告：沒有找到任何表情符號");
                return;
            }

            System.out.println("開始處理表情符號...");
            System.out.println("找到的表情符號數量: " + emojis.size());
            
            for (Emoji emoji : emojis) {
                if (emoji == null) {
                    System.out.println("警告：發現空的表情符號物件");
                    continue;
                }
                
                System.out.println("名稱: " + emoji.getName());
                System.out.println("圖片URL: " + emoji.getImageUrl());
                System.out.println("-------------------");
            }

            EmojiDownloader downloader = new EmojiDownloader();
            String destinationFolder = "./emojis/apple/light/";
            
            new File(destinationFolder).mkdirs();
            
            System.out.println("開始下載表情符號到: " + destinationFolder);
            downloader.downloadEmojiImages(emojis, destinationFolder);
            
        } catch (Exception e) {
            System.err.println("程式執行時發生錯誤：");
            e.printStackTrace();
        }
    }
} 
package emoji;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;

public class EmojiScraper {
    private static final String BASE_URL = "https://emojigraph.org/apple";

    public List<Emoji> scrapeAllEmojis() {
        List<Emoji> allEmojis = new ArrayList<>();
        Map<String, String> emojiMap = new HashMap<>();
        
        try {
            Document doc = fetchPage(BASE_URL);
            Elements emojiLinks = doc.select("#category__first .row.cva a.pim");
            
            Set<String> processedBaseNames = new HashSet<>();
            Map<String, Element> baseToLightSkinTone = new HashMap<>();
            
            // 1st loop for collecting all light skin tone emojis
            for (Element link : emojiLinks) {
                String href = link.attr("href");
                
                if (href.contains("-light-skin-tone") && !href.contains("medium-light")) {
                    String baseName = href.replaceAll("^/|/$", "")
                                        .replaceAll("-light-skin-tone.*$", "")
                                        .replaceAll("-medium.*$", "")
                                        .replaceAll("-dark.*$", "");
                    baseToLightSkinTone.put(baseName, link);
                }
            }
            
            // 2nd loop for marching the deviration of the emoji
            for (Element link : emojiLinks) {
                String href = link.attr("href");
                String baseName = href.replaceAll("^/|/$", "")
                                    .replaceAll("-light-skin-tone.*$", "")
                                    .replaceAll("-medium.*$", "")
                                    .replaceAll("-dark.*$", "");
                

                if (processedBaseNames.contains(baseName)) {
                    continue;
                }
                

                Element linkToUse = baseToLightSkinTone.containsKey(baseName) ? 
                                  baseToLightSkinTone.get(baseName) : link;
                
                Element img = linkToUse.select("img").first();
                if (img != null) {
                    String imageUrl = img.attr("src");
                    if (!imageUrl.startsWith("/media")) continue;
                    
                    String fullImageUrl = "https://emojigraph.org/media/apple" + 
                        imageUrl.replace("/media/72/apple/", "/");
                    
                    processedBaseNames.add(baseName);
                    String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1)
                                           .replaceAll("_[0-9a-f\\-]+(\\.png)$", "$1");
                    String name = baseName.replaceAll("-", " ");
                    emojiMap.put(name, fullImageUrl);
                    
                    Emoji emoji = new Emoji();
                    emoji.setName(fileName.replace(".png", ""));
                    emoji.setImageUrl(fullImageUrl);
                    emoji.setSkinTone(href.contains("light-skin-tone") ? "light" : "default");
                    allEmojis.add(emoji);
                }
            }
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter("emoji_mapping.json")) {
                gson.toJson(emojiMap, writer);
            }
            
        } catch (IOException e) {
            System.err.println("下載失敗: " + e.getMessage());
            e.printStackTrace();
        }
        
        return allEmojis;
    }

    private Document fetchPage(String url) throws IOException {
        return Jsoup.connect(url)
                   .userAgent("Mozilla/5.0")
                   .timeout(10000)
                   .get();
    }
} 
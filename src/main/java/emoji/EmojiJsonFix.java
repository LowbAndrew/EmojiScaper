package emoji;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class EmojiJsonFix {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("emoji_mapping.json");
            Map<String, String> emojiMap = gson.fromJson(reader, 
                new TypeToken<Map<String, String>>(){}.getType());
            reader.close();

            Map<String, String> newEmojiMap = new HashMap<>();
            
            for (Map.Entry<String, String> entry : emojiMap.entrySet()) {
                String name = entry.getKey();
                String url = entry.getValue();
                
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                fileName = fileName.replaceAll("_[0-9a-f\\-]+(\\.png)$", "$1");
                
                String relativePath = "./emojis/apple/light/" + fileName;
                
                newEmojiMap.put(name, relativePath);
            }

            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter("emoji_mapping_local.json")) {
                prettyGson.toJson(newEmojiMap, writer);
            }
            
            System.out.println("JSON 檔案已更新完成！");
            
        } catch (IOException e) {
            System.err.println("處理 JSON 檔案時發生錯誤：");
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * m3u8 下载器
 * @author: 康小安
 * @createDate: 2021/5/18 12:44
 */
public class M3U8Downloader {
    private static String folderPath = "E://meu8dl";
    private static String preUrlPath = "https://cdn.av01.tv/v2/20190907/pred00183/content/";
    private static String rootPath = "E://meu8dl";
    private static String fileName = "aaa.mp4";
    private static int threadQuantity = 10;


    public static void main(String[] args) throws Exception {
        // HTTP 代理，只能代理 HTTP 请求
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "1080");

        // HTTPS 代理，只能代理 HTTPS 请求
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "1080");
        preUrlPath = "https://ms.ggyypro.com/";
        String originUrlPath = "https://ms.ggyypro.com/jd_m3u8/LR52agOI8J7M2hR4yvqbgw/1581195602/jd_hls/iphone/1735.mp4/index.m3u8?e=1581195602&st=5qtxik0XAbTFyYdTjC_k4Q";
        M3U8Downloader downloader = new M3U8Downloader();
        String content = downloader.getIndexFile(originUrlPath);

        List<String> urlList = downloader.analysisIndex(content);
        Map<Integer, String> keyMap = new HashMap<>();
        // keyMap = downloader.downLoadIndexFile(urlList);

        downloader.downLoadIndexFileAsync(urlList, keyMap);
        downloader.composeFile(keyMap);

    }

    public String getIndexFile(String originUrlpath) throws Exception{
        URL url = new URL(originUrlpath);
        //下载资源
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));

        String content = "" ;
        String line;
        while ((line = in.readLine()) != null) {
            content += line + "\n";
        }
        in.close();

        return content;
    }

    public List<String> analysisIndex(String content) throws Exception{
        Pattern pattern = Pattern.compile(".*ts");
        Matcher ma = pattern.matcher(content);
        List<String> list = new ArrayList<String>();
        while(ma.find()){
            list.add(ma.group());
        }

        return list;
    }

    public Map<Integer, String> downLoadIndexFile(List<String> urlList){
        Map<Integer,String> keyFileMap = new HashMap<Integer, String>(16);

        for(int i =0;i<urlList.size();i++){
            String subUrlPath = urlList.get(i);
            String fileOutPath = folderPath + File.separator + i + ".ts";
            keyFileMap.put(i, fileOutPath);
            try{
                downloadNet(preUrlPath + subUrlPath, fileOutPath);
                System.out.println("成功："+ (i + 1) +"/" + urlList.size());
            }catch (Exception e){
                System.err.println("失败："+ (i + 1) +"/" + urlList.size());
            }
        }

        return  keyFileMap;
    }

    private void downloadNet(String fullUrlPath, String fileOutPath) throws Exception {

        //int bytesum = 0;
        int byteread = 0;

        URL url = new URL(fullUrlPath);
        URLConnection conn = url.openConnection();
        InputStream inStream = conn.getInputStream();
        FileOutputStream fs = new FileOutputStream(fileOutPath);

        byte[] buffer = new byte[1204];
        while ((byteread = inStream.read(buffer)) != -1) {
            //bytesum += byteread;
            fs.write(buffer, 0, byteread);
        }
    }

    public void downLoadIndexFileAsync(List<String> urlList, Map<Integer,String> keyFileMap) throws Exception{
        int downloadForEveryThread = (urlList.size() + threadQuantity - 1)/threadQuantity;
        if(downloadForEveryThread == 0) downloadForEveryThread = urlList.size();

        for(int i=0; i<urlList.size();i+=downloadForEveryThread){
            int startIndex = i;
            int endIndex = i + downloadForEveryThread - 1;
            if(endIndex >= urlList.size())
                endIndex = urlList.size() - 1;

            new DownloadThread(urlList, startIndex, endIndex, keyFileMap).start();
        }
    }

    class DownloadThread extends Thread{
        private List<String> urlList;
        private int startIndex;
        private int endIndex;
        private Map<Integer,String> keyFileMap;

        public DownloadThread(List<String> urlList, int startIndex, int endIndex, Map<Integer,String> keyFileMap){
            this.urlList = urlList;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.keyFileMap = keyFileMap;
        }
        @Override
        public void run(){
            for(int i=startIndex;i<=endIndex;i++){
                String subUrlPath = urlList.get(i);
                String fileOutPath = folderPath + File.separator + i + ".ts";
                keyFileMap.put(i, fileOutPath);
                String message = "%s: 线程 " + (startIndex/(endIndex - startIndex) + 1)
                        + ", "+ (i + 1) +"/" + urlList.size() +", 合计: %d";
                try{
                    downloadNet(preUrlPath + subUrlPath, fileOutPath);

                    System.out.println(String.format(message, "成功", keyFileMap.size()));
                }catch (Exception e){
                    System.err.println(String.format(message, "失败", keyFileMap.size()));
                }
            }
        }
    }

    public String composeFile(Map<Integer,String> keyFileMap) throws Exception{

        if(keyFileMap.isEmpty()) return null;

        String fileOutPath = rootPath + File.separator + fileName;
        FileOutputStream fileOutputStream = new FileOutputStream(new File(fileOutPath));
        byte[] bytes = new byte[1024];
        int length = 0;
        for(int i=0; i<keyFileMap.size(); i++){
            String nodePath = keyFileMap.get(i);
            File file = new File(nodePath);
            if(!file.exists())  continue;

            FileInputStream fis = new FileInputStream(file);
            while ((length = fis.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
        }

        return fileName;
    }
}





















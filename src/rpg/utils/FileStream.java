package rpg.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class FileStream {
    String path;
    InputStream inputStream;
    public FileStream(String path) {
        this.path = path;
    }
    public FileStream(InputStream is) {
        this.inputStream = is;
    }

    public boolean write(String content) {
        try {
            FileOutputStream fos;
            OutputStreamWriter osw;
            BufferedWriter bw = null;
            try {
                File file = new File(path);
                if(file.getParentFile() != null) file.mkdirs();
                if(!file.exists()) file.createNewFile();
                fos = new FileOutputStream(file);
                osw = new OutputStreamWriter(fos, "utf-8");
                bw = new BufferedWriter(osw);
                bw.write(content);
                return true;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                if(bw != null) bw.close();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readAsStream() {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNextLine()) {
            builder.append(scanner.nextLine()).append('\n');
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public String read() {
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br = null;
        try {
            try {
                File file = new File(path);
                if(!file.exists()) file.createNewFile();
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                br = new BufferedReader(isr);
                StringBuilder result = new StringBuilder();
                String str;
                while((str = br.readLine()) != null) {
                    result.append(str);
                    result.append("\n");
                }
                return result.toString();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                if(br != null) br.close();
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove() {
        try {
            File file = new File(path);
            if(file.exists()) file.delete();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}

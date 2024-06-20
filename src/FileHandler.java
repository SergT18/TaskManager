import java.io.*;

public class FileHandler {

    String fileName;
    File file;

    public FileHandler(String filename) {
        this.fileName = filename;
        this.file = getFile();
    }

    public String getFileData() {
        try {
            FileReader fr = new FileReader(this.file);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            fr.close();
            br.close();
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeDataToFile(String s){
        try {
            if(!this.file.canWrite()){
                throw new IOException("<<< Невозможно открыть файл для записи >>>");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private File getFile() {
        File file = new File(this.fileName);
        File dir = new File(file.getParent());

        try {

            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return file;

    }

}

package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private String fileName;
    private String fileContent;
    private List<String> fileList;

    public Model() {
        fileName = "";
        fileContent = "";
        fileList = new ArrayList<>();
    }

    public void loadFile(String fileName) throws IOException {
        this.fileName = fileName;
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        fileContent = content.toString();
    }

    public void saveFile(String fileName, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
    }

    public void addFile(String fileName) {
        fileList.add(fileName);
    }

    public List<String> getFileList() {
        return fileList;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileContent() {
        return fileContent;
    }
}

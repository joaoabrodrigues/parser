package br.com.db1.parser.dto;

public class ImportFileRequestDTO {

    private String path;

    public ImportFileRequestDTO() {
    }

    public ImportFileRequestDTO(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

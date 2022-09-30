package com.personal;

public class GrepBuilder {
    
    private String stringToSearch;
    private String directoryToSearchIn;
    private Boolean isRecursive;
    private Boolean isIgnorecase;

    public GrepBuilder setStringToSearch(String stringToSearch) {
        this.stringToSearch = stringToSearch;
        return this;
    }
    public GrepBuilder setDirectoryToSearchIn(String directoryToSearchIn) {
        this.directoryToSearchIn = directoryToSearchIn;
        return this;
    }
    public GrepBuilder setIsRecursive(Boolean isRecursive) {
        this.isRecursive = isRecursive;
        return this;
    }
    public GrepBuilder setIsIgnorecase(Boolean isIgnorecase) {
        this.isIgnorecase = isIgnorecase;
        return this;
    }

    public String getStringToSearch() {
        return stringToSearch;
    }
    public String getDirectoryToSearchIn() {
        return directoryToSearchIn;
    }
    public Boolean getIsRecursive() {
        return isRecursive;
    }
    public Boolean getIsIgnorecase() {
        return isIgnorecase;
    }

    public Grep build() {
        return new Grep(this);
    }

    
}

class PlaceHold {
  public String[] getIncludedDirectories() {
    String[] directories = new String[dirsIncluded.size()];
    dirsIncluded.copyInto(directories);
    return directories;
  }
}

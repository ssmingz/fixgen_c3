class PlaceHold {
  public String[] getIncludedDirectories() {
    String[] directories = new String[dirsIncluded.size()];
    dirsIncluded.copyInto(directories);
    Arrays.sort(directories);
    return directories;
  }
}

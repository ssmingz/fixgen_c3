class PlaceHold {
  public String[] getIncludedDirectories() {
    int count = dirsIncluded.size();
    String[] directories = new String[count];
    for (int i = 0; i < count; i++) {
      directories[i] = ((String) (dirsIncluded.get(i)));
    }
    return directories;
  }
}

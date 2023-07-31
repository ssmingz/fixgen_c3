class PlaceHold {
  public String[] getNotIncludedDirectories() throws TaskException {
    slowScan();
    int count = dirsNotIncluded.size();
    String[] directories = new String[count];
    for (int i = 0; i < count; i++) {
      directories[i] = ((String) (dirsNotIncluded.get(i)));
    }
    return directories;
  }
}

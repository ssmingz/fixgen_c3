class PlaceHold {
  public String[] getExcludedDirectories() throws TaskException {
    slowScan();
    int count = dirsExcluded.size();
    String[] directories = new String[count];
    for (int i = 0; i < count; i++) {
      directories[i] = ((String) (dirsExcluded.elementAt(i)));
    }
    return directories;
  }
}

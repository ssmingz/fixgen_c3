class PlaceHold {
  public String[] getExcludedFiles() throws TaskException {
    slowScan();
    int count = filesExcluded.size();
    String[] files = new String[count];
    for (int i = 0; i < count; i++) {
      files[i] = ((String) (filesExcluded.elementAt(i)));
    }
    return files;
  }
}

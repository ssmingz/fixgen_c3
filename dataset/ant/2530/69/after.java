class PlaceHold {
  public String[] getNotIncludedFiles() throws TaskException {
    slowScan();
    int count = filesNotIncluded.size();
    String[] files = new String[count];
    for (int i = 0; i < count; i++) {
      files[i] = ((String) (filesNotIncluded.get(i)));
    }
    return files;
  }
}

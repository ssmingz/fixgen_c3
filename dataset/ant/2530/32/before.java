class PlaceHold {
  public String[] getIncludedFiles() {
    int count = filesIncluded.size();
    String[] files = new String[count];
    for (int i = 0; i < count; i++) {
      files[i] = ((String) (filesIncluded.elementAt(i)));
    }
    return files;
  }
}

class PlaceHold {
  public String[] getIncludedFiles() {
    if (src == null) {
      return super.getIncludedFiles();
    }
    scanme();
    return matchFileEntries.keySet().toArray(new String[matchFileEntries.size()]);
  }
}

class PlaceHold {
  public String[] getIncludedDirectories() {
    if (src == null) {
      return super.getIncludedDirectories();
    }
    scanme();
    return matchDirEntries.keySet().toArray(new String[matchDirEntries.size()]);
  }
}

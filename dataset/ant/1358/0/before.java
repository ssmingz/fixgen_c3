class PlaceHold {
  public String[] getIncludedDirectories() {
    if (src == null) {
      return super.getIncludedDirectories();
    }
    scanme();
    Set s = matchDirEntries.keySet();
    return ((String[]) (s.toArray(new String[s.size()])));
  }
}

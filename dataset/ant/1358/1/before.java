class PlaceHold {
  public String[] getIncludedFiles() {
    if (src == null) {
      return super.getIncludedFiles();
    }
    scanme();
    Set s = matchFileEntries.keySet();
    return ((String[]) (s.toArray(new String[s.size()])));
  }
}

class PlaceHold {
  public void setFindWord(String word) {
    _findWord = StringOps.replace(word, System.getProperty("line.separator"), "\n");
  }
}

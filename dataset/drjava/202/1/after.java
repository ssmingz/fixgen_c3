class PlaceHold {
  public void setReplaceWord(String word) {
    _replaceWord = StringOps.replace(word, System.getProperty("line.separator"), "\n");
  }
}

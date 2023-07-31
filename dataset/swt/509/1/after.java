class PlaceHold {
  public String getString() {
    char[] buffer = new char[length()];
    getCharacters(buffer);
    return new String(buffer);
  }
}

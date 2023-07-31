class PlaceHold {
  public String getString() {
    char[] buffer = new char[length()];
    getCharacters_(buffer);
    return new String(buffer);
  }
}

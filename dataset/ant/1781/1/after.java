class PlaceHold {
  public void setLine(String line) {
    if (line == null) {
      return;
    }
    parts = translateCommandline(line);
  }
}

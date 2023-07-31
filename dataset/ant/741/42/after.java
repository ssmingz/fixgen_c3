class PlaceHold {
  public void addText(String s) {
    setString(getProject().replaceProperties(s));
  }
}

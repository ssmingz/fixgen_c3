class PlaceHold {
  public void addText(String msg) {
    message += getProject().replaceProperties(msg);
  }
}

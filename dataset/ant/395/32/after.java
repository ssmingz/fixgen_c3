class PlaceHold {
  public void addText(String msg) {
    if (message == null) {
      message = "";
    }
    message += getProject().replaceProperties(msg);
  }
}

class PlaceHold {
  public void addText(String msg) {
    if (message == null) {
      message = "";
    }
    message += project.replaceProperties(msg);
  }
}

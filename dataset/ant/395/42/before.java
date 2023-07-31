class PlaceHold {
  public void addText(String msg) throws TaskException {
    message += project.replaceProperties(msg);
  }
}

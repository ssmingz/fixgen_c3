class PlaceHold {
  public void addText(String msg) throws TaskException {
    message += getProject().replaceProperties(msg);
  }
}

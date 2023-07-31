class PlaceHold {
  public void setHeaderFile(String fileName) {
    if ((fileName == null) || (fileName.trim().length() == 0)) {
      return;
    }
    filename = fileName;
  }
}

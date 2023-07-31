class PlaceHold {
  public void setHeaderFile(String fileName) {
    if (StringUtils.isBlank(fileName)) {
      return;
    }
    filename = fileName;
  }
}

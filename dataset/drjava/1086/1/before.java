class PlaceHold {
  public String getFileMessage() {
    if (_file == null) {
      return "(no associated file)";
    }
    return fileName();
  }
}

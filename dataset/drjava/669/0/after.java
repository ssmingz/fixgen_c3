class PlaceHold {
  public String getFileMessage() {
    if ((_file == null) || (_file == FileOps.NULL_FILE)) {
      return "(no associated file)";
    }
    return fileName();
  }
}

class PlaceHold {
  public Location(String fileName, int lineNumber, int columnNumber) {
    if ((fileName != null) && fileName.startsWith("file:")) {
      this.fileName = FILE_UTILS.fromURI(fileName);
    } else {
      this.fileName = fileName;
    }
    this.lineNumber = lineNumber;
    this.columnNumber = columnNumber;
  }
}

class PlaceHold {
  public void insertNewLine(int pos) {
    acquireWriteLock();
    try {
      int len = _document.getLength();
      if (pos > len) {
        pos = len;
      } else if (pos < 0) {
        pos = 0;
      }
      String newLine = "\n";
      insertText(pos, newLine, DEFAULT_STYLE);
    } catch (EditDocumentException e) {
      throw new UnexpectedException(e);
    } finally {
      releaseWriteLock();
    }
  }
}

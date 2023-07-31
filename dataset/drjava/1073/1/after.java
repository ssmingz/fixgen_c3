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
      String newLine = System.getProperty("line.separator");
      insertText(pos, newLine, DEFAULT_STYLE);
    } catch (DocumentAdapterException e) {
      throw new UnexpectedException(e);
    } finally {
      releaseWriteLock();
    }
  }
}

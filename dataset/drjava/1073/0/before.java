class PlaceHold {
  public void append(String str, String style) throws DocumentAdapterException {
    acquireWriteLock();
    try {
      int offs = _document.getDocLength();
      _addToStyleLists(offs, str, style);
      _document.insertText(offs, str, style);
    } finally {
      releaseWriteLock();
    }
  }
}

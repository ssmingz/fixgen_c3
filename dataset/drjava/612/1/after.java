class PlaceHold {
  public void insertText(int offs, String str, String style) throws DocumentAdapterException {
    writeLock();
    try {
      if (_condition.canInsertText(offs, str, style)) {
        forceInsertText(offs, str, style);
      }
    } finally {
      writeUnlock();
    }
  }
}

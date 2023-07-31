class PlaceHold {
  public void insertText(int offs, String str, String style) throws DocumentAdapterException {
    if (_condition.canInsertText(offs, str, style)) {
      forceInsertText(offs, str, style);
    }
  }
}

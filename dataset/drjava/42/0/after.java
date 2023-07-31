class PlaceHold {
  public String getText(int offset, int length) throws BadLocationException {
    return _defDoc.getText(offset, length);
  }
}

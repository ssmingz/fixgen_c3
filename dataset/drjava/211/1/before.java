class PlaceHold {
  public void insertString(int offs, String str, AttributeSet set) throws BadLocationException {
    if (_condition.canInsertText(offs, str, null)) {
      super.insertString(offs, str, set);
    }
  }
}

class PlaceHold {
  public void setCaret(Caret caret) {
    checkWidget();
    super.setCaret(caret);
    if (caret != null) {
      if (isBidi() == false) {
        caret.setSize(caret.getSize().x, lineHeight);
      }
      setCaretLocation();
      if (isBidi()) {
        setBidiKeyboardLanguage();
      }
    }
  }
}

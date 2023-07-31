class PlaceHold {
  void createWidget() {
    super.createWidget();
    blinkRate = display.getCaretBlinkTime();
    isVisible = true;
    if (parent.getCaret() == null) {
      parent.setCaret(this);
    }
  }
}

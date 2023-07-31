class PlaceHold {
  void createWidget() {
    super.createWidget();
    Display display = parent.getDisplay();
    blinkRate = display.getCaretBlinkTime();
    isVisible = true;
    if (parent.getCaret() == null) {
      parent.setCaret(this);
    }
  }
}

class PlaceHold {
  private int textWidth(GC gc) {
    String text = getText();
    int textWidth = 0;
    if (text != null) {
      int flags = (SWT.DRAW_DELIMITER | SWT.DRAW_TAB) | SWT.DRAW_MNEMONIC;
      textWidth = gc.textExtent(text, flags).x;
    }
    if (textWidth == 0) {
      textWidth = DEFAULT_TEXT_WIDTH;
    }
    return textWidth;
  }
}

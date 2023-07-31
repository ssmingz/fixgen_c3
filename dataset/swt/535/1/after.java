class PlaceHold {
  private int textHeight(GC gc) {
    String text = getText();
    if (text == null) {
      return 0;
    } else {
      int flags = (SWT.DRAW_DELIMITER | SWT.DRAW_TAB) | SWT.DRAW_MNEMONIC;
      return gc.textExtent(text, flags).y;
    }
  }
}

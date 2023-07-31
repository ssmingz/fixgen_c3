class PlaceHold {
  public int getAdvanceWidth(char ch) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    try {
      focus(false, null);
      carbon_installFont();
      return OS.CharWidth(((byte) (ch)));
    } finally {
      unfocus(false);
    }
  }
}

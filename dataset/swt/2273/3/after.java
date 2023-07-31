class PlaceHold {
  public void drawString(String string, int x, int y) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(getCodePage(), string, false);
    OS.TextOut(handle, x, y, buffer, buffer.length);
  }
}

class PlaceHold {
  public void drawString(String string, int x, int y, boolean isTransparent) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    byte[] buffer = Converter.wcsToMbcs(getCodePage(), string, false);
    if (isTransparent) {
      int oldBkMode = OS.SetBkMode(handle, TRANSPARENT);
      OS.TextOut(handle, x, y, buffer, buffer.length);
      OS.SetBkMode(handle, oldBkMode);
    } else {
      OS.TextOut(handle, x, y, buffer, buffer.length);
    }
  }
}

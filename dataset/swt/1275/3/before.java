class PlaceHold {
  public void drawText(String string, int x, int y) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    RECT rect = new RECT();
    OS.SetRect(rect, x, y, 0x7fff, 0x7fff);
    byte[] buffer = Converter.wcsToMbcs(getCodePage(), string, false);
    OS.DrawText(
        handle, buffer, buffer.length, rect, (OS.DT_EXPANDTABS | OS.DT_LEFT) | OS.DT_NOPREFIX);
  }
}

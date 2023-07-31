class PlaceHold {
  public void drawString(String string, int x, int y, boolean isTransparent) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(getCodePage(), string, true);
    int xmString = OS.XmStringCreate(buffer, XmFONTLIST_DEFAULT_TAG);
    if (isTransparent) {
      OS.XmStringDraw(
          data.display,
          data.drawable,
          data.fontList,
          xmString,
          handle,
          x,
          y,
          0x7fffffff,
          XmALIGNMENT_BEGINNING,
          0,
          null);
    } else {
      OS.XmStringDrawImage(
          data.display,
          data.drawable,
          data.fontList,
          xmString,
          handle,
          x,
          y,
          0x7fffffff,
          XmALIGNMENT_BEGINNING,
          0,
          null);
    }
    OS.XmStringFree(xmString);
  }
}

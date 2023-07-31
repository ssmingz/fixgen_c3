class PlaceHold {
  public Point textExtent(String string) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (string.length() == 0) {
      return new Point(0, getFontHeight());
    }
    byte[] textBuffer = Converter.wcsToMbcs(null, string, true);
    int xmString = OS.XmStringGenerate(textBuffer, null, XmCHARSET_TEXT, _MOTIF_DEFAULT_LOCALE);
    if (data.renderTable == 0) {
      createRenderTable();
    }
    int renderTable = data.renderTable;
    int width = OS.XmStringWidth(renderTable, xmString);
    int height = OS.XmStringHeight(renderTable, xmString);
    OS.XmStringFree(xmString);
    return new Point(width, height);
  }
}

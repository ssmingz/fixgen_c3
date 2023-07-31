class PlaceHold {
  public Point textExtent(String string, int flags) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (string.length() == 0) {
      return new Point(0, getFontHeight());
    }
    if (data.renderTable == 0) {
      createRenderTable();
    }
    int renderTable = data.renderTable;
    int tableLength = 0;
    Device device = data.device;
    int[] parseTable = new int[2];
    char[] text = new char[string.length()];
    string.getChars(0, text.length, text, 0);
    if ((flags & SWT.DRAW_DELIMITER) != 0) {
      parseTable[tableLength++] = device.crMapping;
    }
    if ((flags & SWT.DRAW_TAB) != 0) {
      parseTable[tableLength++] = device.tabMapping;
    }
    if ((flags & SWT.DRAW_MNEMONIC) != 0) {
      fixMnemonic(text);
    }
    byte[] buffer = Converter.wcsToMbcs(getCodePage(), text, true);
    int xmString =
        OS.XmStringParseText(
            buffer, 0, XmFONTLIST_DEFAULT_TAG, XmCHARSET_TEXT, parseTable, tableLength, 0);
    int width = OS.XmStringWidth(renderTable, xmString);
    int height = OS.XmStringHeight(renderTable, xmString);
    OS.XmStringFree(xmString);
    return new Point(width, height);
  }
}

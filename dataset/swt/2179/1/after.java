class PlaceHold {
  public void drawText(String string, int x, int y, int flags) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (data.renderTable == 0) {
      createRenderTable();
    }
    int renderTable = data.renderTable;
    char mnemonic = 0;
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
      mnemonic = stripMnemonic(text);
    }
    String codePage = getCodePage();
    byte[] buffer = Converter.wcsToMbcs(codePage, text, true);
    int xmString =
        OS.XmStringParseText(
            buffer, 0, XmFONTLIST_DEFAULT_TAG, XmCHARSET_TEXT, parseTable, tableLength, 0);
    if (mnemonic != 0) {
      byte[] buffer1 = Converter.wcsToMbcs(codePage, new char[] {mnemonic}, true);
      int xmStringUnderline = OS.XmStringCreate(buffer1, XmFONTLIST_DEFAULT_TAG);
      OS.XmStringDrawUnderline(
          data.display,
          data.drawable,
          renderTable,
          xmString,
          handle,
          x,
          y,
          0x7fffffff,
          XmALIGNMENT_BEGINNING,
          0,
          null,
          xmStringUnderline);
      OS.XmStringFree(xmStringUnderline);
    } else if ((flags & SWT.DRAW_TRANSPARENT) != 0) {
      OS.XmStringDraw(
          data.display,
          data.drawable,
          renderTable,
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
          renderTable,
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

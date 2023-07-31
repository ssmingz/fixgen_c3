class PlaceHold {
  public void drawText(String string, int x, int y, int flags) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (string.length() == 0) {
      return;
    }
    setText(string, flags);
    int xmMnemonic = data.xmMnemonic;
    if (xmMnemonic != 0) {
      OS.XmStringDrawUnderline(
          data.display,
          data.drawable,
          data.renderTable,
          data.xmText,
          handle,
          x,
          y,
          0x7fffffff,
          XmALIGNMENT_BEGINNING,
          0,
          null,
          xmMnemonic);
    } else if ((flags & SWT.DRAW_TRANSPARENT) != 0) {
      OS.XmStringDraw(
          data.display,
          data.drawable,
          data.renderTable,
          data.xmText,
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
          data.renderTable,
          data.xmText,
          handle,
          x,
          y,
          0x7fffffff,
          XmALIGNMENT_BEGINNING,
          0,
          null);
    }
  }
}

class PlaceHold {
  public void setFont(int index, Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    if (cellFont == null) {
      if (font == null) {
        return;
      }
      cellFont = new Font[count];
    }
    Font oldFont = cellFont[index];
    if (oldFont == font) {
      return;
    }
    cellFont[index] = font;
    if ((oldFont != null) && oldFont.equals(font)) {
      return;
    }
    if (font != null) {
      parent.customDraw = true;
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    if (index == 0) {
      if ((((parent.style & SWT.VIRTUAL) == 0) && (!cached)) && (!parent.painted)) {
        return;
      }
      int hwnd = parent.handle;
      TVITEM tvItem = new TVITEM();
      tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_TEXT;
      tvItem.hItem = handle;
      tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
      OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
    } else {
      redraw(index, true, false);
    }
  }
}

class PlaceHold {
  public void setFont(int index, Font font) {
    checkWidget();
    if ((font != null) && font.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    int hFont = -1;
    if (font != null) {
      parent.customDraw = true;
      hFont = font.handle;
    }
    if (cellFont == null) {
      int hwndHeader = OS.SendMessage(parent.handle, LVM_GETHEADER, 0, 0);
      int itemCount = OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0);
      cellFont = new int[itemCount];
      for (int i = 0; i < itemCount; i++) {
        cellFont[i] = -1;
      }
    }
    cellFont[index] = hFont;
    redraw();
  }
}

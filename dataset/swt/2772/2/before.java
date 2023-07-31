class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    int topIndex = getTopIndex();
    if (topIndex != 0) {
      setRedraw(false);
      setTopIndex(0);
    }
    if (itemHeight != (-1)) {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      OS.SetWindowLong(handle, GWL_STYLE, bits | OS.LVS_OWNERDRAWFIXED);
    }
    super.setFont(font);
    if (itemHeight != (-1)) {
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      OS.SetWindowLong(handle, GWL_STYLE, bits & (~OS.LVS_OWNERDRAWFIXED));
    }
    setScrollWidth(null, true);
    if (topIndex != 0) {
      setTopIndex(topIndex);
      setRedraw(true);
    }
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    OS.InvalidateRect(hwndHeader, null, true);
  }
}

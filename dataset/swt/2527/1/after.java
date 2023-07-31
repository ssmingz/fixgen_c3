class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    super.setFont(font);
    setScrollWidth(true);
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    OS.InvalidateRect(hwndHeader, null, true);
    int bits = OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0);
    if ((bits & OS.LVS_EX_GRIDLINES) == 0) {
      return;
    }
    bits = OS.GetWindowLong(handle, GWL_STYLE);
    if ((bits & OS.LVS_NOCOLUMNHEADER) != 0) {
      return;
    }
    setRowHeight();
  }
}

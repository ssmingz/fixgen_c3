class PlaceHold {
  public void setHeaderVisible(boolean show) {
    checkWidget();
    int newBits = OS.GetWindowLong(handle, GWL_STYLE);
    newBits &= ~OS.LVS_NOCOLUMNHEADER;
    if (!show) {
      newBits |= OS.LVS_NOCOLUMNHEADER;
    }
    int oldIndex = getTopIndex();
    OS.SetWindowLong(handle, GWL_STYLE, newBits);
    int newIndex = getTopIndex();
    if (newIndex != 0) {
      setRedraw(false);
      setTopIndex(0);
    }
    if (show) {
      int bits = ((int) (OS.SendMessage(handle, LVM_GETEXTENDEDLISTVIEWSTYLE, 0, 0)));
      if ((bits & OS.LVS_EX_GRIDLINES) != 0) {
        fixItemHeight(false);
      }
    }
    setTopIndex(oldIndex);
    if (newIndex != 0) {
      setRedraw(true);
    }
    updateHeaderToolTips();
  }
}

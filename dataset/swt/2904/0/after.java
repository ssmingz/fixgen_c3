class PlaceHold {
  boolean isTabItem() {
    Control[] tabList = parent._getTabList();
    if (tabList != null) {
      for (int i = 0; i < tabList.length; i++) {
        if (tabList[i] == this) {
          return false;
        }
      }
    }
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    if ((bits & OS.WS_TABSTOP) != 0) {
      return false;
    }
    long code = OS.SendMessage(handle, WM_GETDLGCODE, 0, 0);
    if ((code & OS.DLGC_STATIC) != 0) {
      return false;
    }
    if ((code & OS.DLGC_WANTALLKEYS) != 0) {
      return false;
    }
    if ((code & OS.DLGC_WANTARROWS) != 0) {
      return false;
    }
    if ((code & OS.DLGC_WANTTAB) != 0) {
      return false;
    }
    return true;
  }
}

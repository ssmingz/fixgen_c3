class PlaceHold {
  boolean isTabGroup() {
    Control[] tabList = parent._getTabList();
    if (tabList != null) {
      for (int i = 0; i < tabList.length; i++) {
        if (tabList[i] == this) {
          return true;
        }
      }
    }
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    return (bits & OS.WS_TABSTOP) != 0;
  }
}

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
    int code = traversalCode(0, null);
    return (code & (SWT.TRAVERSE_ARROW_PREVIOUS | SWT.TRAVERSE_ARROW_NEXT)) != 0;
  }
}

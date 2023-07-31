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
    int code = traversalCode(0, null);
    if ((code & (SWT.TRAVERSE_ARROW_PREVIOUS | SWT.TRAVERSE_ARROW_NEXT)) != 0) {
      return false;
    }
    return (code & (SWT.TRAVERSE_TAB_PREVIOUS | SWT.TRAVERSE_TAB_NEXT)) != 0;
  }
}

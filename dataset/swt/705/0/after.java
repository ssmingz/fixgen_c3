class PlaceHold {
  public void showItem(CTabItem item) {
    checkWidget();
    if (item == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int index = indexOf(item);
    if (index == (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int idx = -1;
    for (int i = 0; i < priority.length; i++) {
      if (priority[i] == index) {
        idx = i;
        break;
      }
    }
    if (mru) {
      int[] newPriority = new int[priority.length];
      System.arraycopy(priority, 0, newPriority, 1, idx);
      System.arraycopy(priority, idx + 1, newPriority, idx + 1, (priority.length - idx) - 1);
      newPriority[0] = index;
      priority = newPriority;
    }
    if (item.showing) {
      return;
    }
    updateFolder(REDRAW_TABS);
  }
}

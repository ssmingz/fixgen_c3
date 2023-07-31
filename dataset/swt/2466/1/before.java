class PlaceHold {
  public void setTopItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int hItem = item.handle;
    boolean fixScroll = fixScroll(hItem);
    if (fixScroll) {
      OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
    }
    OS.SendMessage(handle, TVM_SELECTITEM, TVGN_FIRSTVISIBLE, hItem);
    if (fixScroll) {
      OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
    }
  }
}

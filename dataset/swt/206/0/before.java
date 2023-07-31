class PlaceHold {
  public void deselect(int index) {
    checkWidget();
    if (index == (-1)) {
      return;
    }
    if ((style & SWT.SINGLE) != 0) {
      int oldIndex = OS.SendMessage(handle, LB_GETCURSEL, 0, 0);
      if (oldIndex == OS.LB_ERR) {
        return;
      }
      if (oldIndex == index) {
        OS.SendMessage(handle, LB_SETCURSEL, -1, 0);
      }
      return;
    }
    OS.SendMessage(handle, LB_SETSEL, 0, index);
  }
}

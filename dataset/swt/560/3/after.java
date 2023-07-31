class PlaceHold {
  public void setDefaultItem(MenuItem item) {
    checkWidget();
    int command = -1;
    if (item != null) {
      if (item.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      command = item.id;
    }
    OS.SetMenuDefaultItem(handle, command, MF_BYCOMMAND);
    redraw();
  }
}

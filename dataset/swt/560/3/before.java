class PlaceHold {
  public void setDefaultItem(MenuItem item) {
    checkWidget();
    int command = -1;
    if (item != null) {
      command = item.id;
    }
    OS.SetMenuDefaultItem(handle, command, MF_BYCOMMAND);
    redraw();
  }
}

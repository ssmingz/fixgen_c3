class PlaceHold {
  public void deselect(int index) {
    checkWidget();
    int selection = OS.SendMessage(handle, CB_GETCURSEL, 0, 0);
    if (index != selection) {
      return;
    }
    OS.SendMessage(handle, CB_SETCURSEL, -1, 0);
    sendEvent(Modify);
  }
}

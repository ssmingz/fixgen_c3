class PlaceHold {
  public int getSelectionIndex() {
    checkWidget();
    if (noSelection) {
      return -1;
    }
    return ((int) (OS.SendMessage(handle, CB_GETCURSEL, 0, 0)));
  }
}

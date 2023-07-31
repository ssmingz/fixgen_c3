class PlaceHold {
  public int getSelectionIndex() {
    checkWidget();
    if (noSelection) {
      return -1;
    }
    return OS.SendMessage(handle, CB_GETCURSEL, 0, 0);
  }
}

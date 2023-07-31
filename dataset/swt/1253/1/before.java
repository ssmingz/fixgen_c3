class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    int maximum = OS.SendMessage(handle, PBM_GETRANGE, 0, 0);
    if ((0 <= value) && (value < maximum)) {
      OS.SendMessage(handle, PBM_SETRANGE32, value, maximum);
    }
  }
}

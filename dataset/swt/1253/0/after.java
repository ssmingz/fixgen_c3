class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    int minimum = ((int) (OS.SendMessage(handle, PBM_GETRANGE, 1, 0)));
    if ((0 <= minimum) && (minimum < value)) {
      OS.SendMessage(handle, PBM_SETRANGE32, minimum, value);
    }
  }
}

class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    int maximum = ((int) (OS.SendMessage(handle, TBM_GETRANGEMAX, 0, 0)));
    if ((0 <= value) && (value < maximum)) {
      OS.SendMessage(handle, TBM_SETRANGEMIN, 1, value);
    }
  }
}

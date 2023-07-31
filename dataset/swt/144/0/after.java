class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    int minimum = ((int) (OS.SendMessage(handle, TBM_GETRANGEMIN, 0, 0)));
    if ((0 <= minimum) && (minimum < value)) {
      OS.SendMessage(handle, TBM_SETRANGEMAX, 1, value);
    }
  }
}

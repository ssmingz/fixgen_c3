class PlaceHold {
  public void setMonth(int month) {
    checkWidget();
    dateRec.month = ((short) (month));
    OS.SetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
    OS.GetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec, null);
    redraw();
  }
}

class PlaceHold {
  public void setHour(int hour) {
    checkWidget();
    dateRec.hour = ((short) (hour));
    OS.SetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
    OS.GetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec, null);
  }
}

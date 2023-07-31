class PlaceHold {
  public void setMinute(int minute) {
    checkWidget();
    dateRec.minute = ((short) (minute));
    OS.SetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
    OS.GetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec, null);
  }
}

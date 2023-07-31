class PlaceHold {
  public void setDay(int day) {
    checkWidget();
    dateRec.day = ((short) (day));
    OS.SetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
    OS.GetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec, null);
  }
}

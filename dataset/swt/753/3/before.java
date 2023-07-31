class PlaceHold {
  public void setYear(int year) {
    checkWidget();
    dateRec.year = ((short) (year));
    OS.SetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
    OS.GetControlData(
        handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec, null);
  }
}

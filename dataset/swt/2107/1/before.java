class PlaceHold {
  public void setMonth(int month) {
    checkWidget();
    if (!isValid(Calendar.MONTH, month)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      calendar.set(Calendar.MONTH, month);
    } else {
      dateRec.month = ((short) (month + 1));
      OS.SetControlData(
          handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
      OS.GetControlData(
          handle,
          ((short) (kControlEntireControl)),
          kControlClockLongDateTag,
          sizeof,
          dateRec,
          null);
    }
    redraw();
  }
}

class PlaceHold {
  public void setHours(int hours) {
    checkWidget();
    if (!isValid(Calendar.HOUR_OF_DAY, hours)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      calendar.set(Calendar.HOUR_OF_DAY, hours);
    } else {
      dateRec.hour = ((short) (hours));
      OS.SetControlData(
          handle, ((short) (kControlEntireControl)), kControlClockLongDateTag, sizeof, dateRec);
      OS.GetControlData(
          handle,
          ((short) (kControlEntireControl)),
          kControlClockLongDateTag,
          sizeof,
          dateRec,
          null);
      if ((style & SWT.DATE) != 0) {
        dateAndTime.hour = ((short) (hours));
      }
    }
    redraw();
  }
}

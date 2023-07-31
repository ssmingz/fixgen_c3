class PlaceHold {
  public void setMinutes(int minutes) {
    checkWidget();
    if (!isValid(Calendar.MINUTE, minutes)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      calendar.set(Calendar.MINUTE, minutes);
    } else {
      dateRec.minute = ((short) (minutes));
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
        dateAndTime.minute = ((short) (minutes));
      }
    }
    redraw();
  }
}

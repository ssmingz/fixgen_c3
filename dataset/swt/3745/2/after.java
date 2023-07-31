class PlaceHold {
  public void setDay(int day) {
    checkWidget();
    if ((style & SWT.CALENDAR) != 0) {
      setDay(day, false);
    } else {
      calendar.set(Calendar.DAY_OF_MONTH, day);
      updateControl();
    }
  }
}

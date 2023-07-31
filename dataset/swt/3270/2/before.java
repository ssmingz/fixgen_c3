class PlaceHold {
  public void setDay(int day) {
    checkWidget();
    if (!isValidDate(getYear(), getMonth(), day)) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      this.day = day;
      OS.gtk_calendar_select_day(handle, day);
    } else {
      calendar.set(Calendar.DAY_OF_MONTH, day);
      updateControl();
    }
  }
}

class PlaceHold {
  public void setMonth(int month) {
    checkWidget();
    if (!isValidDate(getYear(), month, getDay())) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      this.month = month;
      OS.gtk_calendar_select_month(handle, month, year);
    } else {
      calendar.set(Calendar.MONTH, month);
      updateControl();
    }
  }
}

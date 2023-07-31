class PlaceHold {
  public void setYear(int year) {
    checkWidget();
    if (!isValidDate(year, getMonth(), getDay())) {
      return;
    }
    if ((style & SWT.CALENDAR) != 0) {
      this.year = year;
      OS.gtk_calendar_select_month(handle, month, year);
    } else {
      calendar.set(Calendar.YEAR, year);
      updateControl();
    }
  }
}

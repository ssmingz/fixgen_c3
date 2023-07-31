class PlaceHold {
  public void setYear(int year) {
    checkWidget();
    if (!isValidDate(year, getMonth(), getDay())) {
      return;
    }
    if (isCalendar()) {
      this.year = year;
      OS.gtk_calendar_select_month(calendarHandle, month, year);
    } else {
      calendar.set(Calendar.YEAR, year);
      updateControl();
    }
  }
}

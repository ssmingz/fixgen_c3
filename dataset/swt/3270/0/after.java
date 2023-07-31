class PlaceHold {
  public void setMonth(int month) {
    checkWidget();
    if (!isValidDate(getYear(), month, getDay())) {
      return;
    }
    if (isCalendar()) {
      this.month = month;
      OS.gtk_calendar_select_month(calendarHandle, month, year);
    } else {
      calendar.set(Calendar.MONTH, month);
      updateControl();
    }
  }
}

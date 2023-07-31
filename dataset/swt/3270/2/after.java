class PlaceHold {
  public void setDay(int day) {
    checkWidget();
    if (!isValidDate(getYear(), getMonth(), day)) {
      return;
    }
    if (isCalendar()) {
      this.day = day;
      OS.gtk_calendar_select_day(calendarHandle, day);
    } else {
      calendar.set(Calendar.DAY_OF_MONTH, day);
      updateControl();
    }
  }
}

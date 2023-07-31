class PlaceHold {
  public void setYear(int year) {
    checkWidget();
    if ((year < MIN_YEAR) || (year > MAX_YEAR)) {
      return;
    }
    this.year = year;
    OS.gtk_calendar_select_month(handle, month, year);
  }
}

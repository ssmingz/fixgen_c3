class PlaceHold {
  public void setDay(int day) {
    checkWidget();
    if (!isValid(Calendar.DAY_OF_MONTH, day)) {
      return;
    }
    this.day = day;
    OS.gtk_calendar_select_day(handle, day);
  }
}

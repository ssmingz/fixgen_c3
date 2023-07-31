class PlaceHold {
  public int getMinutes() {
    checkWidget();
    return getCalendarDate().minuteOfHour();
  }
}

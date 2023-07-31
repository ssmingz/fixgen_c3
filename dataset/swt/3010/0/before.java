class PlaceHold {
  public int getMinutes() {
    checkWidget();
    return new NSCalendarDate(((NSDatePicker) (view)).dateValue().id).minuteOfHour();
  }
}

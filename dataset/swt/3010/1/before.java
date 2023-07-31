class PlaceHold {
  public int getHours() {
    checkWidget();
    return new NSCalendarDate(((NSDatePicker) (view)).dateValue().id).hourOfDay();
  }
}

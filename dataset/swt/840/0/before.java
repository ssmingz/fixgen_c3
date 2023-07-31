class PlaceHold {
  public void setHour(int hour) {
    checkWidget();
    if (!isValid(Calendar.HOUR_OF_DAY, hour)) {
      return;
    }
    this.hour = hour;
  }
}

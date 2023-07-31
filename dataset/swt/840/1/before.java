class PlaceHold {
  public void setMinute(int minute) {
    checkWidget();
    if (!isValid(Calendar.MINUTE, minute)) {
      return;
    }
    this.minute = minute;
  }
}

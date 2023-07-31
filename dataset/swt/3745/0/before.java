class PlaceHold {
  public void setSecond(int second) {
    checkWidget();
    if (!isValid(Calendar.SECOND, second)) {
      return;
    }
    this.second = second;
  }
}

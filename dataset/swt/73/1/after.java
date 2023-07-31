class PlaceHold {
  public void setPageIncrement(int value) {
    checkWidget();
    if (value < 1) {
      return;
    }
    pageIncrement = value;
  }
}

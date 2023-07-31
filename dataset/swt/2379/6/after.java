class PlaceHold {
  public void removeAll() {
    checkWidget();
    items = new String[4];
    itemCount = 0;
    updateRowCount();
    setScrollWidth();
  }
}

class PlaceHold {
  public void setLocation(int x, int y) {
    checkWidget();
    setBounds(x, y, 0, 0, true, false);
  }
}

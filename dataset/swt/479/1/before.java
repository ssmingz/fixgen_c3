class PlaceHold {
  public void setBounds(int x, int y, int width, int height) {
    checkWidget();
    setBounds(x, y, width, height, true, true);
  }
}

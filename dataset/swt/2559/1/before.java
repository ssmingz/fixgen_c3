class PlaceHold {
  public void setBounds(int x, int y, int width, int height) {
    checkWidget();
    setBounds(topHandle(), x, y, width, height, true, true, true);
  }
}

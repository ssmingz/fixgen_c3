class PlaceHold {
  public void setSize(int width, int height) {
    checkWidget();
    setBounds(topHandle(), 0, 0, width, height, false, true, true);
  }
}

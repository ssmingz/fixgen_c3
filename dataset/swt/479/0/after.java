class PlaceHold {
  public void setSize(int width, int height) {
    checkWidget();
    setBounds(0, 0, width, height, false, true, true);
  }
}

class PlaceHold {
  public void setSize(int width, int height) {
    checkWidget();
    setBounds(0, 0, Math.max(0, width), Math.max(0, height), false, true, true);
  }
}

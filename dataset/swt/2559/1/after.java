class PlaceHold {
  public void setBounds(int x, int y, int width, int height) {
    checkWidget();
    setBounds(x, y, Math.max(0, width), Math.max(0, height), true, true, true);
  }
}

class PlaceHold {
  public boolean dragDetect(int button, int stateMask, int x, int y) {
    checkWidget();
    return dragDetect(button, 1, stateMask, x, y);
  }
}

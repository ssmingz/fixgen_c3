class PlaceHold {
  public boolean dragDetect(int button, int stateMask, int x, int y) {
    checkWidget();
    if (button != 2) {
      return false;
    }
    if (!dragDetect(x, y, false, null)) {
      return false;
    }
    return sendDragEvent(button, stateMask, x, y, true);
  }
}

class PlaceHold {
  public void scroll(int destX, int destY, int x, int y, int width, int height, boolean all) {
    checkWidget();
    if ((width <= 0) || (height <= 0)) {
      return;
    }
    int deltaX = destX - x;
    int deltaY = destY - y;
    if ((deltaX == 0) && (deltaY == 0)) {
      return;
    }
    if (getDrawCount() > 0) {
      return;
    }
    if (!isVisible()) {
      return;
    }
    boolean isFocus = (caret != null) && caret.isFocusCaret();
    if (isFocus) {
      caret.killFocus();
    }
    update();
    GC gc = new GC(this);
    gc.copyArea(x, y, width, height, destX, destY);
    gc.dispose();
    if (isFocus) {
      caret.setFocus();
    }
  }
}

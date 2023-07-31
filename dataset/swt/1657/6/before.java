class PlaceHold {
  public void setHeaderVisible(boolean value) {
    checkWidget();
    if (header.getVisible() == value) {
      return;
    }
    header.setVisible(value);
    updateVerticalBar();
    redraw();
  }
}

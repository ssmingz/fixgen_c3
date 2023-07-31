class PlaceHold {
  public void setHeaderVisible(boolean value) {
    checkWidget();
    if (header.getVisible() == value) {
      return;
    }
    headerHideToolTip();
    header.setVisible(value);
    updateVerticalBar();
    redraw();
  }
}

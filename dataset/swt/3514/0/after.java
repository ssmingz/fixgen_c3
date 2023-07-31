class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    parent.setScrollbarVisible(this, visible);
  }
}

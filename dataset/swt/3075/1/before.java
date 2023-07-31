class PlaceHold {
  public void setMaximizeVisible(boolean visible) {
    checkWidget();
    if (showMax == visible) {
      return;
    }
    showMax = visible;
    updateItems();
    redraw();
  }
}

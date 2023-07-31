class PlaceHold {
  public void setMinimizeVisible(boolean visible) {
    checkWidget();
    if (showMin == visible) {
      return;
    }
    showMin = visible;
    updateItems();
    redraw();
  }
}

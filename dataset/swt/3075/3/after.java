class PlaceHold {
  public void setMinimizeVisible(boolean visible) {
    checkWidget();
    if (showMin == visible) {
      return;
    }
    showMin = visible;
    updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
  }
}

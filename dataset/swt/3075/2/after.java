class PlaceHold {
  void setChevronVisible(boolean visible) {
    checkWidget();
    if (chevronVisible == visible) {
      return;
    }
    chevronVisible = visible;
    updateFolder(UPDATE_TAB_HEIGHT | REDRAW);
  }
}

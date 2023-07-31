class PlaceHold {
  public Color getBackground(int index) {
    checkWidget();
    Table parent = getParent();
    if ((0 > index) || (index >= parent.getColumnCount())) {
      error(ERROR_INVALID_RANGE);
    }
    return getBackground();
  }
}

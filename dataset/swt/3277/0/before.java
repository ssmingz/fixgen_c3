class PlaceHold {
  public Color getForeground(int index) {
    checkWidget();
    Table parent = getParent();
    if ((0 > index) || (index >= parent.getColumnCount())) {
      error(ERROR_INVALID_RANGE);
    }
    return getForeground();
  }
}

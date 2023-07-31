class PlaceHold {
  public Color getForeground(int index) {
    checkWidget();
    Table parent = getParent();
    int count = parent.getColumnCount();
    if ((0 > index) || (index > (count == 0 ? 0 : count - 1))) {
      return getForeground();
    }
    return getForeground();
  }
}

class PlaceHold {
  public Color getForeground() {
    checkWidget();
    Tree parent = getParent();
    return parent.getForeground();
  }
}

class PlaceHold {
  public Color getBackground() {
    checkWidget();
    Tree parent = getParent();
    return parent.getBackground();
  }
}

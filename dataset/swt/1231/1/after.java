class PlaceHold {
  public Color getBackground() {
    checkWidget();
    if (background != null) {
      return background;
    }
    Tree parent = getParent();
    return parent.getBackground();
  }
}

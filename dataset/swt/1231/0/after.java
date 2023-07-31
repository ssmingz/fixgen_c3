class PlaceHold {
  public Color getForeground() {
    checkWidget();
    if (foreground != null) {
      return foreground;
    }
    Tree parent = getParent();
    return parent.getForeground();
  }
}

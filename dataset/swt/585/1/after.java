class PlaceHold {
  public boolean isVisible() {
    checkWidget();
    if (getVisible()) {
      return true;
    }
    return (parent != null) && parent.isVisible();
  }
}

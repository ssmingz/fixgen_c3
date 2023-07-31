class PlaceHold {
  public void setGrayed(boolean value) {
    checkWidget();
    if ((parent.getStyle() & SWT.CHECK) == 0) {
      return;
    }
    if (grayed == value) {
      return;
    }
    grayed = value;
    Rectangle bounds = getCheckboxBounds();
    parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
  }
}

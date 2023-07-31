class PlaceHold {
  public void setChecked(boolean value) {
    checkWidget();
    if ((parent.getStyle() & SWT.CHECK) == 0) {
      return;
    }
    if (checked == value) {
      return;
    }
    checked = value;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    if (isInViewport()) {
      Rectangle bounds = getCheckboxBounds();
      parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
    }
  }
}

class PlaceHold {
  public void setTopRight(Control control) {
    checkWidget();
    if ((control != null) && (control.getParent() != this)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    topRight = control;
    if (setButtonBounds()) {
      redraw();
    }
  }
}

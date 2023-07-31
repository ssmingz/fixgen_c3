class PlaceHold {
  public void moveAbove(Control control) {
    checkWidget();
    if (control.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    setZOrder(control, true);
  }
}

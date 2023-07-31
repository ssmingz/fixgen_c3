class PlaceHold {
  public void moveAbove(Control control) {
    checkWidget();
    if (control != null) {
      if (control.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if (parent != control.parent) {
        return;
      }
    }
    setZOrder(control, true);
  }
}

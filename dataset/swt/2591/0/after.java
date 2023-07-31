class PlaceHold {
  public void setControl(Control control) {
    checkWidget();
    if (control != null) {
      if (control.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if (control.parent != parent) {
        error(ERROR_INVALID_PARENT);
      }
    }
    if ((style & SWT.SEPARATOR) == 0) {
      return;
    }
    this.control = control;
    if ((control != null) && (!control.isDisposed())) {
      control.setBounds(getBounds());
    }
    redrawWidget(handle, false);
  }
}

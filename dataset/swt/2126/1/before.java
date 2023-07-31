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
    Control oldControl = this.control;
    this.control = control;
    if (oldControl != null) {
      OS.PtReParentWidget(oldControl.handle, parent.handle);
    }
    if ((control != null) && (!control.isDisposed())) {
      OS.PtReParentWidget(control.handle, handle);
      control.setBounds(getBounds());
    }
  }
}

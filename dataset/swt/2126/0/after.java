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
    Control newControl = control;
    Control oldControl = this.control;
    if (oldControl == newControl) {
      return;
    }
    this.control = newControl;
    if (oldControl != null) {
      OS.gtk_widget_reparent(oldControl.topHandle(), parent.parentingHandle());
    }
    if (newControl != null) {
      OS.gtk_widget_reparent(newControl.topHandle(), handle);
      OS.gtk_widget_hide(separatorHandle);
      resizeControl();
    } else {
      OS.gtk_widget_show(separatorHandle);
    }
  }
}

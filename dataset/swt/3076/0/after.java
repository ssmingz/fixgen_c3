class PlaceHold {
  void fixFocus(Control focusControl) {
    Shell shell = getShell();
    Control control = this;
    while ((control != shell) && ((control = control.parent) != null)) {
      if (control.setFocus()) {
        return;
      }
    }
    shell.setSavedFocus(focusControl);
    int focusHandle = shell.vboxHandle;
    gtk_widget_set_can_focus(focusHandle, true);
    OS.gtk_widget_grab_focus(focusHandle);
    if (isDisposed()) {
      return;
    }
    OS.GTK_WIDGET_UNSET_FLAGS(focusHandle, GTK_CAN_FOCUS);
  }
}

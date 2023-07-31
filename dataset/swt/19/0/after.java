class PlaceHold {
  public void setDefaultButton(Button button) {
    checkWidget();
    long buttonHandle = 0;
    if (button != null) {
      if (button.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if (button.menuShell() != this) {
        error(ERROR_INVALID_PARENT);
      }
      buttonHandle = button.handle;
    }
    saveDefault = defaultButton = button;
    OS.gtk_window_set_default(topHandle(), buttonHandle);
  }
}

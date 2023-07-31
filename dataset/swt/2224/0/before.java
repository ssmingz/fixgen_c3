class PlaceHold {
  public void setDefaultButton(Button button) {
    checkWidget();
    int buttonHandle = 0;
    if (button != null) {
      if (button.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      buttonHandle = button.handle;
    }
    saveDefault = defaultButton = button;
    OS.gtk_window_set_default(topHandle(), buttonHandle);
  }
}

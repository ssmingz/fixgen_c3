class PlaceHold {
  public void setDefaultButton(Button button) {
    checkWidget();
    if (button != null) {
      if (button.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if ((button.style & SWT.PUSH) == 0) {
        return;
      }
    }
    if (button == defaultButton) {
      return;
    }
    if (defaultButton != null) {
      if (!defaultButton.isDisposed()) {
        defaultButton.setDefault(false);
      }
    }
    defaultButton = button;
    if (defaultButton != null) {
      if (!defaultButton.isDisposed()) {
        defaultButton.setDefault(true);
      }
    }
  }
}

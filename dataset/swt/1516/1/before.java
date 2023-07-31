class PlaceHold {
  void setDefaultButton(Button button, boolean save) {
    if (button == null) {
      if (defaultButton == saveDefault) {
        if (save) {
          saveDefault = null;
        }
        return;
      }
    } else {
      if ((button.style & SWT.PUSH) == 0) {
        return;
      }
      if (button == defaultButton) {
        return;
      }
    }
    if (defaultButton != null) {
      if (!defaultButton.isDisposed()) {
        defaultButton.setDefault(false);
      }
    }
    if ((defaultButton = button) == null) {
      defaultButton = saveDefault;
    }
    if (defaultButton != null) {
      if (!defaultButton.isDisposed()) {
        defaultButton.setDefault(true);
      }
    }
    if (save) {
      saveDefault = defaultButton;
    }
    if ((saveDefault != null) && saveDefault.isDisposed()) {
      saveDefault = null;
    }
  }
}

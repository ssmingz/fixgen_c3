class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    if (readOnlyButton.getSelection()) {
      style |= SWT.READ_ONLY;
    }
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    spinner1 = new Spinner(spinnerGroup, style);
  }
}

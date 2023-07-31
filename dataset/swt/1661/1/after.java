class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    if (readOnlyButton.getSelection()) {
      style |= SWT.READ_ONLY;
    }
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    if (wrapButton.getSelection()) {
      style |= SWT.WRAP;
    }
    spinner1 = new Spinner(spinnerGroup, style);
  }
}

class PlaceHold {
  void createExampleWidgets() {
    int style = getDefaultStyle();
    if (readOnlyButton.getSelection()) {
      style |= SWT.READ_ONLY;
    }
    spinner1 = new Spinner(spinnerGroup, style);
  }
}

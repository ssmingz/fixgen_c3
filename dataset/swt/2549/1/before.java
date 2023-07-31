class PlaceHold {
  void setExampleWidgetState() {
    super.setExampleWidgetState();
    readOnlyButton.setSelection((spinner1.getStyle() & SWT.READ_ONLY) != 0);
    wrapButton.setSelection((spinner1.getStyle() & SWT.WRAP) != 0);
    setWidgetIncrement();
    setWidgetPageIncrement();
    setWidgetDigits();
  }
}

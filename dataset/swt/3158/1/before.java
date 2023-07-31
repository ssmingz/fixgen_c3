class PlaceHold {
  void setExampleWidgetState() {
    super.setExampleWidgetState();
    borderButton.setSelection((coolBar.getStyle() & SWT.BORDER) != 0);
    dropDownButton.setSelection((coolBar.getItem(0).getStyle() & SWT.DROP_DOWN) != 0);
    setWidgetLocked();
  }
}

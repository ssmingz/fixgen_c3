class PlaceHold {
  void setExampleWidgetState() {
    super.setExampleWidgetState();
    setWidgetHeaderVisible();
    setWidgetLinesVisible();
    fullSelectionButton.setSelection((table1.getStyle() & SWT.FULL_SELECTION) != 0);
  }
}

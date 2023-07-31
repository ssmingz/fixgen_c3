class PlaceHold {
  void setExampleWidgetState() {
    super.setExampleWidgetState();
    setWidgetHeaderVisible();
    setWidgetLinesVisible();
    checkButton.setSelection((table1.getStyle() & SWT.CHECK) != 0);
    fullSelectionButton.setSelection((table1.getStyle() & SWT.FULL_SELECTION) != 0);
  }
}

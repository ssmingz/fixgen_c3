class PlaceHold {
  void setExampleWidgetState() {
    super.setExampleWidgetState();
    wrapButton.setSelection((styledText.getStyle() & SWT.WRAP) != 0);
    readOnlyButton.setSelection((styledText.getStyle() & SWT.READ_ONLY) != 0);
    fullSelectionButton.setSelection((styledText.getStyle() & SWT.FULL_SELECTION) != 0);
    horizontalButton.setEnabled((styledText.getStyle() & SWT.MULTI) != 0);
    verticalButton.setEnabled((styledText.getStyle() & SWT.MULTI) != 0);
  }
}

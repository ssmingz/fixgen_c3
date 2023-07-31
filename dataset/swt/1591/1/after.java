class PlaceHold {
  void setExampleWidgetState() {
    super.setExampleWidgetState();
    wrapButton.setSelection((text.getStyle() & SWT.WRAP) != 0);
    readOnlyButton.setSelection((text.getStyle() & SWT.READ_ONLY) != 0);
    wrapButton.setEnabled((text.getStyle() & SWT.MULTI) != 0);
    horizontalButton.setEnabled((text.getStyle() & SWT.MULTI) != 0);
    verticalButton.setEnabled((text.getStyle() & SWT.MULTI) != 0);
  }
}

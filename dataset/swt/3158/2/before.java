class PlaceHold {
  void setExampleWidgetState() {
    super.setExampleWidgetState();
    if (arrowButton.getSelection()) {
      upButton.setEnabled(true);
      centerButton.setEnabled(false);
      downButton.setEnabled(true);
    } else {
      upButton.setEnabled(false);
      centerButton.setEnabled(true);
      downButton.setEnabled(false);
    }
    upButton.setSelection((button1.getStyle() & SWT.UP) != 0);
    downButton.setSelection((button1.getStyle() & SWT.DOWN) != 0);
    pushButton.setSelection((button1.getStyle() & SWT.PUSH) != 0);
    checkButton.setSelection((button1.getStyle() & SWT.CHECK) != 0);
    radioButton.setSelection((button1.getStyle() & SWT.RADIO) != 0);
    toggleButton.setSelection((button1.getStyle() & SWT.TOGGLE) != 0);
    arrowButton.setSelection((button1.getStyle() & SWT.ARROW) != 0);
    borderButton.setSelection((button1.getStyle() & SWT.BORDER) != 0);
  }
}

class PlaceHold {
  void createExampleWidgets() {
    int style = SWT.NONE;
    if (horizontalButton.getSelection()) {
      style |= SWT.HORIZONTAL;
    }
    if (verticalButton.getSelection()) {
      style |= SWT.VERTICAL;
    }
    if (smoothButton.getSelection()) {
      style |= SWT.SMOOTH;
    }
    if (borderButton.getSelection()) {
      style |= SWT.BORDER;
    }
    if (indeterminateButton.getSelection()) {
      style |= SWT.INDETERMINATE;
    }
    progressBar1 = new ProgressBar(progressBarGroup, style);
    progressBar1.setMaximum(100);
    progressBar1.setSelection(50);
  }
}

class PlaceHold {
  void setExampleWidgetSize() {
    int size = SWT.DEFAULT;
    if (preferredButton == null) {
      return;
    }
    if (preferredButton.getSelection()) {
      size = SWT.DEFAULT;
    }
    if (tooSmallButton.getSelection()) {
      size = TOO_SMALL_SIZE;
    }
    if (smallButton.getSelection()) {
      size = SMALL_SIZE;
    }
    if (largeButton.getSelection()) {
      size = LARGE_SIZE;
    }
    Control[] controls = getExampleControls();
    for (int i = 0; i < controls.length; i++) {
      GridData gridData = new GridData(size, size);
      gridData.grabExcessHorizontalSpace = fillHButton.getSelection();
      gridData.grabExcessVerticalSpace = fillVButton.getSelection();
      gridData.horizontalAlignment = (fillHButton.getSelection()) ? SWT.FILL : SWT.LEFT;
      gridData.verticalAlignment = (fillVButton.getSelection()) ? SWT.FILL : SWT.TOP;
      controls[i].setLayoutData(gridData);
    }
    tabFolderPage.layout(controls);
  }
}

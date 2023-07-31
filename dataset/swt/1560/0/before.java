class PlaceHold {
  public void paste() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return;
    }
  }
}

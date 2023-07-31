class PlaceHold {
  protected void checkDevice() {
    if (xDisplay == 0) {
      SWT.error(ERROR_DEVICE_DISPOSED);
    }
  }
}

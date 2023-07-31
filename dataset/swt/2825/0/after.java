class PlaceHold {
  protected void checkDevice() {
    if (fGDeviceHandle == 0) {
      SWT.error(ERROR_DEVICE_DISPOSED);
    }
  }
}

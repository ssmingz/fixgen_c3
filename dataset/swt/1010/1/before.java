class PlaceHold {
  public void wake() {
    if (isDisposed()) {
      error(ERROR_DEVICE_DISPOSED);
    }
  }
}

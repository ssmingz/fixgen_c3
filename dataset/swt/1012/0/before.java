class PlaceHold {
  public void asyncExec(Runnable runnable) {
    if (isDisposed()) {
      error(ERROR_DEVICE_DISPOSED);
    }
    synchronizer.asyncExec(runnable);
  }
}

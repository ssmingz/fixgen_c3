class PlaceHold {
  public void asyncExec(Runnable runnable) {
    synchronized (Device.class) {
      if (isDisposed()) {
        error(ERROR_DEVICE_DISPOSED);
      }
      synchronizer.asyncExec(runnable);
    }
  }
}

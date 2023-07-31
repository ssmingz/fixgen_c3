class PlaceHold {
  public void syncExec(Runnable runnable) {
    Synchronizer synchronizer;
    synchronized (Device.class) {
      if (isDisposed()) {
        error(ERROR_DEVICE_DISPOSED);
      }
      synchronizer = this.synchronizer;
    }
    synchronizer.syncExec(runnable);
  }
}

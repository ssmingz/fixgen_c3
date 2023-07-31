class PlaceHold {
  public void syncExec(Runnable runnable) {
    Synchronizer synchronizer;
    synchronized (Device.class) {
      if (isDisposed()) {
        error(ERROR_DEVICE_DISPOSED);
      }
      synchronizer = this.synchronizer;
    }
    NSAutoreleasePool localPool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    try {
      synchronizer.syncExec(runnable);
    } finally {
      localPool.release();
    }
  }
}

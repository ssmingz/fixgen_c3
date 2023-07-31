class PlaceHold {
  public boolean isEmpty() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      return OS.EmptyRgn(handle);
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

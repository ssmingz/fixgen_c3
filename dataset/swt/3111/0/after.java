class PlaceHold {
  public void translate(int x, int y) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      OS.OffsetRgn(handle, ((short) (x)), ((short) (y)));
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

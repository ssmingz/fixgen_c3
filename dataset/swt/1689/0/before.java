class PlaceHold {
  public void addPath(Path path) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (path == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (path.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      handle.appendBezierPath(path.handle);
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

class PlaceHold {
  public void translate(Point pt) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pt == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      translate(pt.x, pt.y);
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

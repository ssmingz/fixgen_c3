class PlaceHold {
  public void subtract(int[] pointArray) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (pointArray.length < 2) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      long polyRgn = polyRgn(pointArray, pointArray.length);
      OS.DiffRgn(handle, polyRgn, handle);
      OS.DisposeRgn(polyRgn);
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

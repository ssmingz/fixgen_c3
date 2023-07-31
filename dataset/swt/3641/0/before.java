class Path {
  public Path(Device device, Path path, float flatness) {
    super(device);
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      if (path == null) {
        SWT.error(ERROR_NULL_ARGUMENT);
      }
      if (path.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      flatness = Math.max(0, flatness);
      if (flatness == 0) {
        handle = new NSBezierPath(path.handle.copy().id);
      } else {
        float defaultFlatness = NSBezierPath.defaultFlatness();
        NSBezierPath.setDefaultFlatness(flatness);
        handle = path.handle.bezierPathByFlatteningPath();
        handle.retain();
        NSBezierPath.setDefaultFlatness(defaultFlatness);
      }
      if (handle == null) {
        SWT.error(ERROR_NO_HANDLES);
      }
      init();
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

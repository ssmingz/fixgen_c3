class Path {
  public Path(Device device, Path path, float flatness) {
    super(device);
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
      double defaultFlatness = NSBezierPath.defaultFlatness();
      NSBezierPath.setDefaultFlatness(flatness);
      handle = path.handle.bezierPathByFlatteningPath();
      NSBezierPath.setDefaultFlatness(defaultFlatness);
    }
    if (handle == null) {
      SWT.error(ERROR_NO_HANDLES);
    }
    init();
  }
}

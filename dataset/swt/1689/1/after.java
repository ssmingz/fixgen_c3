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
    OS.CGPathAddPath(handle, null, path.handle);
    moved = false;
    closed = path.closed;
  }
}

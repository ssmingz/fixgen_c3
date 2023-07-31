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
    moved = false;
    int copy = Cairo.cairo_copy_path(path.handle);
    if (copy == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    Cairo.cairo_append_path(handle, copy);
    Cairo.cairo_path_destroy(copy);
    closed = path.closed;
  }
}

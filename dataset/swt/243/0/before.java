class PlaceHold {
  public void fillPath(Path path) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (path == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (path.handle == 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    checkGC(FILL);
    OS.PathGeometry_FillRule(
        path.handle,
        data.fillRule == SWT.FILL_EVEN_ODD ? OS.FillRule_EvenOdd : OS.FillRule_Nonzero);
    OS.DrawingContext_DrawGeometry(handle, data.brush, 0, path.handle);
  }
}

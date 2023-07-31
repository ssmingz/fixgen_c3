class PlaceHold {
  public void add(Rectangle rect) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (rect == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if ((rect.width < 0) || (rect.height < 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int rectRgn = OS.CreateRectRgn(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
    OS.CombineRgn(handle, handle, rectRgn, RGN_OR);
    OS.DeleteObject(rectRgn);
  }
}

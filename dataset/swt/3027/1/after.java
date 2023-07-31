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
    XRectangle xRect = new XRectangle();
    xRect.x = ((short) (rect.x));
    xRect.y = ((short) (rect.y));
    xRect.width = ((short) (rect.width));
    xRect.height = ((short) (rect.height));
    OS.XUnionRectWithRegion(xRect, handle, handle);
  }
}

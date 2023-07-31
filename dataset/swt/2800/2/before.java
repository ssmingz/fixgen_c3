class PlaceHold {
  public void add(Rectangle rect) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (rect == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    XRectangle xRect = new XRectangle();
    xRect.x = ((short) (rect.x));
    xRect.y = ((short) (rect.y));
    xRect.width = ((short) (rect.width));
    xRect.height = ((short) (rect.height));
    OS.XUnionRectWithRegion(xRect, handle, handle);
  }
}

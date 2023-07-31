class PlaceHold {
  public void translate(Point pt) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pt == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    translate(pt.x, pt.y);
  }
}

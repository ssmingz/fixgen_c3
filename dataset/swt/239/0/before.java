class PlaceHold {
  public void setRegion(Region region) {
    checkWidget();
    if ((style & SWT.NO_TRIM) == 0) {
      return;
    }
    if (!OS.XtIsRealized(shellHandle)) {
      realizeWidget();
    }
    int xDisplay = OS.XtDisplay(shellHandle);
    if (xDisplay == 0) {
      return;
    }
    int xWindow = OS.XtWindow(shellHandle);
    if (xWindow == 0) {
      return;
    }
    if (region != null) {
      OS.XShapeCombineRegion(xDisplay, xWindow, ShapeBounding, 0, 0, region.handle, ShapeSet);
    } else {
      OS.XShapeCombineMask(xDisplay, xWindow, ShapeBounding, 0, 0, 0, ShapeSet);
    }
    this.region = region;
  }
}

class PlaceHold {
  void setZOrder(int control, int otheControl, boolean above) {
    int inOp = (above) ? OS.kHIViewZOrderBelow : OS.kHIViewZOrderAbove;
    int oldRgn = 0;
    boolean drawing = isDrawing(control);
    if (drawing) {
      oldRgn = getVisibleRegion(control, false);
    }
    OS.HIViewSetZOrder(control, inOp, otheControl);
    invalidateVisibleRegion(control);
    if (drawing) {
      int newRgn = getVisibleRegion(control, false);
      if (above) {
        OS.DiffRgn(newRgn, oldRgn, newRgn);
      } else {
        OS.DiffRgn(oldRgn, newRgn, newRgn);
      }
      int window = OS.GetControlOwner(control);
      invalWindowRgn(window, newRgn);
      OS.DisposeRgn(oldRgn);
      OS.DisposeRgn(newRgn);
    }
  }
}

class PlaceHold {
  public void setRegion(Region region) {
    checkWidget();
    if ((style & SWT.NO_TRIM) == 0) {
      return;
    }
    if ((region != null) && region.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (region == null) {
      rgnRect = null;
    } else if (rgnRect == null) {
      rgnRect = new Rect();
      OS.GetWindowBounds(shellHandle, ((short) (kWindowStructureRgn)), rgnRect);
      OS.SetRect(
          rgnRect,
          ((short) (0)),
          ((short) (0)),
          ((short) (rgnRect.right - rgnRect.left)),
          ((short) (rgnRect.bottom - rgnRect.top)));
    }
    this.region = region;
    if (drawing) {
      reshape = true;
    } else {
      OS.ReshapeCustomWindow(shellHandle);
      if (OS.HIVIEW) {
        redrawWidget(handle, true);
      }
    }
  }
}

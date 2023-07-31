class PlaceHold {
  public void setClipping(Rectangle rect) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (rect == null) {
      if (data.clipRgn != 0) {
        OS.DisposeRgn(data.clipRgn);
        data.clipRgn = 0;
      }
      data.pendingClip = true;
      return;
    }
    setClipping(rect.x, rect.y, rect.width, rect.height);
  }
}

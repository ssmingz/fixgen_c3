class PlaceHold {
  public void setClipping(Region region) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (region == null) {
      if (data.clipRgn != 0) {
        OS.DisposeRgn(data.clipRgn);
        data.clipRgn = 0;
      }
    } else {
      if (data.clipRgn == 0) {
        data.clipRgn = OS.NewRgn();
      }
      OS.CopyRgn(region.handle, data.clipRgn);
    }
  }
}

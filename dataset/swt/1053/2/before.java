class PlaceHold {
  public void setClipping(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (data.clipRgn == 0) {
      data.clipRgn = OS.NewRgn();
    }
    OS.SetRectRgn(
        data.clipRgn, ((short) (x)), ((short) (y)), ((short) (x + width)), ((short) (y + height)));
    fPendingClip = true;
  }
}

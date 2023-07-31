class Pattern {
  public Pattern(
      Device device,
      float x1,
      float y1,
      float x2,
      float y2,
      Color color1,
      int alpha1,
      Color color2,
      int alpha2) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color1 == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color1.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (color2 == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color2.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    this.device = device;
    device.checkGDIP();
    int colorRef1 = color1.handle;
    int rgb = (((colorRef1 >> 16) & 0xff) | (colorRef1 & 0xff00)) | ((colorRef1 & 0xff) << 16);
    int foreColor = Gdip.Color_new(((alpha1 & 0xff) << 24) | rgb);
    if ((x1 == x2) && (y1 == y2)) {
      handle = Gdip.SolidBrush_new(foreColor);
      if (handle == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
    } else {
      int colorRef2 = color2.handle;
      rgb = (((colorRef2 >> 16) & 0xff) | (colorRef2 & 0xff00)) | ((colorRef2 & 0xff) << 16);
      int backColor = Gdip.Color_new(((alpha2 & 0xff) << 24) | rgb);
      PointF p1 = new PointF();
      p1.X = x1;
      p1.Y = y1;
      PointF p2 = new PointF();
      p2.X = x2;
      p2.Y = y2;
      handle = Gdip.LinearGradientBrush_new(p1, p2, foreColor, backColor);
      if (handle == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      if ((alpha1 != 0xff) || (alpha2 != 0xff)) {
        int a = ((int) (((alpha1 & 0xff) * 0.5F) + ((alpha2 & 0xff) * 0.5F)));
        int r = ((int) ((((colorRef1 & 0xff) >> 0) * 0.5F) + (((colorRef2 & 0xff) >> 0) * 0.5F)));
        int g =
            ((int) ((((colorRef1 & 0xff00) >> 8) * 0.5F) + (((colorRef2 & 0xff00) >> 8) * 0.5F)));
        int b =
            ((int)
                ((((colorRef1 & 0xff0000) >> 16) * 0.5F)
                    + (((colorRef2 & 0xff0000) >> 16) * 0.5F)));
        int midColor = Gdip.Color_new((((a << 24) | (r << 16)) | (g << 8)) | b);
        Gdip.LinearGradientBrush_SetInterpolationColors(
            handle, new int[] {foreColor, midColor, backColor}, new float[] {0, 0.5F, 1}, 3);
        Gdip.Color_delete(midColor);
      }
      Gdip.Color_delete(backColor);
    }
    Gdip.Color_delete(foreColor);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}

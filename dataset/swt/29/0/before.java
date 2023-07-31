class Pattern {
  public Pattern(Device device, Image image) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (image == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (image.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    this.device = device;
    device.checkGDIP();
    int[] gdipImage = image.createGdipImage();
    int img = gdipImage[0];
    int width = Gdip.Image_GetWidth(img);
    int height = Gdip.Image_GetHeight(img);
    handle = Gdip.TextureBrush_new(img, WrapModeTile, 0, 0, width, height);
    Gdip.Bitmap_delete(img);
    if (gdipImage[1] != 0) {
      int hHeap = OS.GetProcessHeap();
      OS.HeapFree(hHeap, 0, gdipImage[1]);
    }
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}

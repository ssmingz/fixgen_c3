class PlaceHold {
  public void dispose() {
    if (handle == 0) {
      return;
    }
    int nullPen = OS.GetStockObject(NULL_PEN);
    int oldPen = OS.SelectObject(handle, nullPen);
    OS.DeleteObject(oldPen);
    int nullBrush = OS.GetStockObject(NULL_BRUSH);
    int oldBrush = OS.SelectObject(handle, nullBrush);
    OS.DeleteObject(oldBrush);
    int hNullBitmap = data.hNullBitmap;
    if (hNullBitmap != 0) {
      OS.SelectObject(handle, hNullBitmap);
      data.hNullBitmap = 0;
    }
    Image image = data.image;
    if (image != null) {
      image.memGC = null;
    }
    Device device = data.device;
    drawable.internal_dispose_GC(handle, data);
    drawable = null;
    handle = 0;
    data.image = null;
    data.ps = null;
    if (device.tracking) {
      device.dispose_Object(this);
    }
    data.device = null;
    data = null;
  }
}

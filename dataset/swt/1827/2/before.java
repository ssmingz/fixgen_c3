class GC {
  public GC(Drawable drawable, int style) {
    if (drawable == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    GCData data = new GCData();
    data.style = checkStyle(style);
    int hDC = drawable.internal_new_GC(data);
    Device device = data.device;
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    data.device = device;
    init(drawable, data, hDC);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}

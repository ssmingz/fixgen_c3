class GC {
  public GC(Drawable drawable, int style) {
    int flags = OS.PtEnter(0);
    try {
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
      this.device = data.device = device;
      init(drawable, data, hDC);
      if (device.tracking) {
        device.new_Object(this);
      }
    } finally {
      if (flags >= 0) {
        OS.PtLeave(flags);
      }
    }
  }
}

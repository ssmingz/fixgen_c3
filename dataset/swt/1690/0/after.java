class GC {
  public GC(Drawable drawable, int style) {
    if (drawable == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      GCData data = new GCData();
      data.style = checkStyle(style);
      long contextId = drawable.internal_new_GC(data);
      Device device = data.device;
      if (device == null) {
        device = Device.getDevice();
      }
      if (device == null) {
        SWT.error(ERROR_NULL_ARGUMENT);
      }
      this.device = data.device = device;
      init(drawable, data, contextId);
      init();
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

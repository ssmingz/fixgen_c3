class Device {
  public Device(DeviceData data) {
    synchronized (Device.class) {
      if (data != null) {
        debug = data.debug;
        tracking = data.tracking;
      }
      if (tracking) {
        errors = new Error[128];
        objects = new Object[128];
        trackingLock = new Object();
      }
      create(data);
      init();
      register(this);
      systemFont = getSystemFont();
    }
  }
}

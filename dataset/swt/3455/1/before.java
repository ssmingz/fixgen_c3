class Device {
  public Device(DeviceData data) {
    synchronized (CREATE_OBJECT) {
      if (data != null) {
        debug = data.debug;
        tracking = data.tracking;
      }
      create(data);
      init();
      if (tracking) {
        errors = new Error[128];
        objects = new Object[128];
        trackingLock = new Object();
      }
    }
  }
}

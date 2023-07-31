class Device {
  public Device(DeviceData data) {
    if (data != null) {
      debug = data.debug;
      tracking = data.tracking;
    }
    create(data);
    init();
    if (tracking) {
      errors = new Error[128];
      objects = new Object[128];
    }
  }
}

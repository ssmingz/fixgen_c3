class Transform {
  public Transform(Device device, float m11, float m12, float m21, float m22, float dx, float dy) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    device.checkGDIP();
    handle = Gdip.Matrix_new(m11, m12, m21, m22, dx, dy);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}

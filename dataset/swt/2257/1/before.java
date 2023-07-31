class Transform {
  public Transform(Device device, float m11, float m12, float m21, float m22, float dx, float dy) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    device.checkCairo();
    handle = new double[6];
    if (handle == null) {
      SWT.error(ERROR_NO_HANDLES);
    }
    Cairo.cairo_matrix_init(handle, m11, m12, m21, m22, dx, dy);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}

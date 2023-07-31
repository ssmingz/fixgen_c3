class Transform {
  public Transform(Device device, float m11, float m12, float m21, float m22, float dx, float dy) {
    super(device);
    this.device.checkGDIP();
    handle = Gdip.Matrix_new(m11, m12, m21, m22, dx, dy);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    init();
  }
}

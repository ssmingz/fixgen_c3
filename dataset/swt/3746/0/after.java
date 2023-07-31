class Path {
  public Path(Device device, PathData data) {
    this(device);
    if (data == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    init(data);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}

class Path {
  public Path(Device device, PathData data) {
    this(device);
    init(data);
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}

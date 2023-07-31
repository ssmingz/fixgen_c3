class Path {
  public Path(Device device) {
    super(device);
    this.device.checkGDIP();
    handle = Gdip.GraphicsPath_new(FillModeAlternate);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    init();
  }
}

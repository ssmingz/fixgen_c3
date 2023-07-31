class Image {
  public Image(Device device, InputStream stream) {
    super(device);
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      init(new ImageData(stream));
      init();
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

class Image {
  public Image(Device device, String filename) {
    super(device);
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      init(new ImageData(filename));
      init();
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

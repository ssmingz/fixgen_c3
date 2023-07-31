class PlaceHold {
  public void test_removeImageLoaderListenerLorg_eclipse_swt_graphics_ImageLoaderListener() {
    ImageLoader loader = new ImageLoader();
    ImageLoaderListener[] listeners = new ImageLoaderListener[COUNT];
    for (int i = 0; i < COUNT; i++) {
      listeners[i] =
          new ImageLoaderListener() {
            public void imageDataLoaded(ImageLoaderEvent e) {}
          };
      loader.addImageLoaderListener(listeners[i]);
    }
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      loader.removeImageLoaderListener(listeners[i]);
    }
    meter.stop();
    disposeMeter(meter);
  }
}

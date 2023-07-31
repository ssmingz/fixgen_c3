class PlaceHold {
  public void test_notifyListenersLorg_eclipse_swt_graphics_ImageLoaderEvent() {
    ImageLoader loader = new ImageLoader();
    ImageLoaderEvent event = new ImageLoaderEvent(loader, null, 0, true);
    for (int i = 0; i < COUNT; i++) {
      loader.addImageLoaderListener(
          new ImageLoaderListener() {
            public void imageDataLoaded(ImageLoaderEvent e) {}
          });
    }
    PerformanceMeter meter = createMeter();
    meter.start();
    for (int i = 0; i < COUNT; i++) {
      loader.notifyListeners(event);
    }
    meter.stop();
    disposeMeter(meter);
  }
}

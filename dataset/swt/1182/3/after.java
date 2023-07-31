class PlaceHold {
  public void dispose() {
    if (handle == null) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    int xDisplay = device.xDisplay;
    int pixel = handle.pixel;
    if (device.colorRefCount != null) {
      if ((--device.colorRefCount[pixel]) == 0) {
        device.xcolors[pixel] = null;
      }
    }
    int colormap = OS.XDefaultColormap(xDisplay, OS.XDefaultScreen(xDisplay));
    OS.XFreeColors(xDisplay, colormap, new int[] {pixel}, 1, 0);
    handle = null;
    if (device.tracking) {
      device.dispose_Object(this);
    }
    device = null;
  }
}

class PlaceHold {
  public void dispose() {
    if (handle == null) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    int pixel = handle.pixel;
    if (device.colorRefCount != null) {
      if ((--device.colorRefCount[pixel]) == 0) {
        device.gdkColors[pixel] = null;
      }
    }
    int colormap = OS.gdk_colormap_get_system();
    OS.gdk_colormap_free_colors(colormap, handle, 1);
    handle = null;
    if (device.tracking) {
      device.dispose_Object(this);
    }
    device = null;
  }
}

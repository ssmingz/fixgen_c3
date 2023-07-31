class PlaceHold {
  public void internal_dispose_GC(int context, GCData data) {
    checkWidget();
    if (data != null) {
      if (data.paintEvent == 0) {
        if (data.visibleRgn != 0) {
          OS.DisposeRgn(data.visibleRgn);
          data.visibleRgn = 0;
        }
        int index = 0;
        while ((index < gcs.length) && (gcs[index] != data)) {
          index++;
        }
        if (index < gcs.length) {
          gcs[index] = null;
          index = 0;
          while ((index < gcs.length) && (gcs[index] == null)) {
            index++;
          }
          if (index == gcs.length) {
            gcs = null;
          }
        }
      }
    }
    OS.CGContextSynchronize(context);
    OS.CGContextRelease(context);
  }
}

class PlaceHold {
  public static GC cocoa_new(Drawable drawable, GCData data) {
    GC gc = new GC();
    long context = drawable.internal_new_GC(data);
    gc.device = data.device;
    gc.init(drawable, data, context);
    return gc;
  }
}

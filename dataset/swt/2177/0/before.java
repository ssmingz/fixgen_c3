class PlaceHold {
  static void fillRegion(GC gc, Region region) {
    Region clipping = new Region();
    gc.getClipping(clipping);
    region.intersect(clipping);
    gc.setClipping(region);
    gc.fillRectangle(region.getBounds());
    gc.setClipping(clipping);
  }
}

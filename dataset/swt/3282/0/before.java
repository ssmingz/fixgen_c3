class PlaceHold {
  public void getClipping(Region region) {
    if (region == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int hRegion = region.handle;
    if (data.clipRgn == 0) {
      int[] width = new int[1];
      int[] height = new int[1];
      int[] unused = new int[1];
      OS.gdk_window_get_geometry(data.drawable, unused, unused, width, height, unused);
      hRegion = OS.gdk_region_new();
      GdkRectangle rect = new GdkRectangle();
      rect.x = 0;
      rect.y = 0;
      rect.width = ((short) (width[0]));
      rect.height = ((short) (height[0]));
      region.handle = OS.gdk_region_union_with_rect(hRegion, rect);
      return;
    }
    hRegion = OS.gdk_region_new();
    region.handle = OS.gdk_regions_union(data.clipRgn, hRegion);
  }
}

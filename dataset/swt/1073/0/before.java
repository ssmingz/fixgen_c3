class PlaceHold {
  public void dispose() {
    if (handle == 0) {
      return;
    }
    int clipRgn = data.clipRgn;
    if (clipRgn != 0) {
      OS.XDestroyRegion(clipRgn);
    }
    Image image = data.image;
    if (image != null) {
      image.memGC = null;
    }
    int renderTable = data.renderTable;
    if (renderTable != 0) {
      OS.XmRenderTableFree(renderTable);
    }
    drawable.internal_dispose_GC(handle, data);
    data.display =
        data.drawable = data.colormap = data.fontList = data.clipRgn = data.renderTable = 0;
    drawable = null;
    data.device = null;
    data.image = null;
    data = null;
    handle = 0;
  }
}

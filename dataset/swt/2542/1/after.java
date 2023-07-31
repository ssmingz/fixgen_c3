class PlaceHold {
  int drawItemProc(
      int browser, int id, int property, int itemState, int theRect, int gdDepth, int colorDevice) {
    int index = id - 1;
    if (!((0 <= index) && (index < items.length))) {
      return OS.noErr;
    }
    TreeItem item = items[index];
    Rect rect = new Rect();
    OS.memcpy(rect, theRect, sizeof);
    int x = rect.left;
    int y = rect.top;
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    Rect controlRect = new Rect();
    OS.GetControlBounds(handle, controlRect);
    x -= controlRect.left;
    y -= controlRect.top;
    GC gc = paintGC;
    if (gc == null) {
      GCData data = new GCData();
      int[] port = new int[1];
      OS.GetPort(port);
      data.port = port[0];
      gc = GC.carbon_new(this, data);
    }
    int clip = OS.NewRgn();
    OS.GetClip(clip);
    OS.OffsetRgn(clip, ((short) (-controlRect.left)), ((short) (-controlRect.top)));
    gc.setClipping(Region.carbon_new(display, clip));
    OS.DisposeRgn(clip);
    Color background = item.getBackground();
    gc.setBackground(background);
    gc.fillRectangle(x, y, width, height);
    Image image = item.image;
    if (image != null) {
      Rectangle bounds = image.getBounds();
      gc.drawImage(
          image,
          0,
          0,
          bounds.width,
          bounds.height,
          x,
          y + ((height - bounds.height) / 2),
          bounds.width,
          bounds.height);
      x += bounds.width + 2;
    }
    Font font = item.getFont();
    gc.setFont(font);
    Point extent = gc.stringExtent(item.text);
    if ((itemState & OS.kDataBrowserItemIsSelected) != 0) {
      gc.setForeground(display.getSystemColor(COLOR_LIST_SELECTION_TEXT));
      gc.setBackground(display.getSystemColor(COLOR_LIST_SELECTION));
      gc.fillRectangle(x - 1, y, extent.x + 2, height);
    } else {
      Color foreground = item.getForeground();
      gc.setForeground(foreground);
    }
    gc.drawString(item.text, x, y + ((height - extent.y) / 2));
    if (gc != paintGC) {
      gc.dispose();
    }
    return OS.noErr;
  }
}

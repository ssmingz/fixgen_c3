class PlaceHold {
  int calculateWidth(int index, GC gc) {
    if ((index == 0) && (width != (-1))) {
      return width;
    }
    int width = 0;
    Image image = getImage(index);
    String text = getText(index);
    if (image != null) {
      width += image.getBounds().width + parent.getGap();
    }
    if ((text != null) && (text.length() > 0)) {
      width += gc.stringExtent(text).x;
    }
    if (parent.hooks(MeasureItem)) {
      Event event = new Event();
      event.item = this;
      event.index = index;
      event.gc = gc;
      short[] height = new short[1];
      OS.GetDataBrowserTableViewRowHeight(parent.handle, height);
      event.width = width;
      event.height = height[0];
      parent.sendEvent(MeasureItem, event);
      if (parent.itemHeight == (-1)) {
        parent.itemHeight = event.height;
        OS.SetDataBrowserTableViewRowHeight(parent.handle, ((short) (event.height)));
      }
      width = event.width;
    }
    if (index == 0) {
      this.width = width;
    }
    return width;
  }
}

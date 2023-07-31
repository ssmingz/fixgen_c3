class PlaceHold {
  int drawItemProc(
      int browser, int id, int property, int itemState, int theRect, int gdDepth, int colorDevice) {
    int index = id - 1;
    if (!((0 <= index) && (index < itemCount))) {
      return OS.noErr;
    }
    int columnIndex = 0;
    if (columnCount > 0) {
      for (columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        if (columns[columnIndex].id == property) {
          break;
        }
      }
      if (columnIndex == columnCount) {
        return OS.noErr;
      }
    }
    lastIndexOf = index;
    TableItem item = _getItem(index);
    if (!item.cached) {
      if ((style & SWT.VIRTUAL) != 0) {
        Event event = new Event();
        event.item = item;
        ignoreRedraw = true;
        sendEvent(SetData, event);
        if (isDisposed()) {
          return OS.noErr;
        }
        ignoreRedraw = false;
        if (setScrollWidth(item)) {
          Rect rect = new Rect();
          if (OS.GetDataBrowserItemPartBounds(
                  handle, id, property, kDataBrowserPropertyEnclosingPart, rect)
              == OS.noErr) {
            redrawWidget(handle, rect.left, rect.top, rect.right, rect.bottom, false);
          }
          return OS.noErr;
        }
      }
      item.cached = true;
    }
    Rect rect = new Rect();
    OS.memcpy(rect, theRect, sizeof);
    int x = rect.left;
    int y = rect.top;
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    boolean selected = (itemState & OS.kDataBrowserItemIsSelected) != 0;
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
    Rect itemRect = new Rect();
    OS.GetDataBrowserItemPartBounds(
        handle, id, property, kDataBrowserPropertyEnclosingPart, itemRect);
    OS.OffsetRect(itemRect, ((short) (-controlRect.left)), ((short) (-controlRect.top)));
    if (selected && ((style & SWT.FULL_SELECTION) != 0)) {
      gc.setBackground(display.getSystemColor(COLOR_LIST_SELECTION));
      gc.fillRectangle(
          itemRect.left,
          itemRect.top,
          itemRect.right - itemRect.left,
          itemRect.bottom - itemRect.top);
    } else {
      gc.setBackground(item.getBackground(columnIndex));
      gc.fillRectangle(
          itemRect.left,
          itemRect.top,
          itemRect.right - itemRect.left,
          itemRect.bottom - itemRect.top);
    }
    int rectRgn = OS.NewRgn();
    OS.RectRgn(rectRgn, rect);
    OS.OffsetRgn(rectRgn, ((short) (-controlRect.left)), ((short) (-controlRect.top)));
    OS.SectRgn(rectRgn, clip, clip);
    OS.DisposeRgn(rectRgn);
    gc.setClipping(Region.carbon_new(display, clip));
    OS.DisposeRgn(clip);
    Image image = item.getImage(columnIndex);
    String text = item.getText(columnIndex);
    gc.setFont(item.getFont(columnIndex));
    Point extent = gc.stringExtent(text);
    int itemWidth = extent.x;
    Rectangle imageBounds = null;
    if (image != null) {
      imageBounds = image.getBounds();
      itemWidth += this.imageBounds.width + 2;
    }
    if (columnCount != 0) {
      TableColumn column = columns[columnIndex];
      if ((column.style & SWT.CENTER) != 0) {
        x += (width - itemWidth) / 2;
      }
      if ((column.style & SWT.RIGHT) != 0) {
        x += width - itemWidth;
      }
    }
    if (image != null) {
      gc.drawImage(
          image,
          0,
          0,
          imageBounds.width,
          imageBounds.height,
          x,
          y + ((height - this.imageBounds.height) / 2),
          this.imageBounds.width,
          this.imageBounds.height);
      x += this.imageBounds.width + 2;
    }
    if (selected) {
      gc.setForeground(display.getSystemColor(COLOR_LIST_SELECTION_TEXT));
      if ((columnIndex == 0) && ((style & SWT.FULL_SELECTION) == 0)) {
        gc.setBackground(display.getSystemColor(COLOR_LIST_SELECTION));
        gc.fillRectangle(x - 1, y, extent.x + 2, height);
      }
    } else {
      Color foreground = item.getForeground(columnIndex);
      gc.setForeground(foreground);
    }
    gc.drawString(text, x, y + ((height - extent.y) / 2), true);
    if (gc != paintGC) {
      gc.dispose();
    }
    return OS.noErr;
  }
}

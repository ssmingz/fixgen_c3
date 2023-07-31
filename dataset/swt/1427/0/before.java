class PlaceHold {
  int drawItemProc(
      int browser, int id, int property, int itemState, int theRect, int gdDepth, int colorDevice) {
    int index = getIndex(id);
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
    Rect rect = new Rect();
    lastIndexOf = index;
    TableItem item = _getItem(index);
    if ((style & SWT.VIRTUAL) != 0) {
      if (!item.cached) {
        if (!checkData(item, false)) {
          return OS.noErr;
        }
        if (setScrollWidth(item)) {
          if (OS.GetDataBrowserItemPartBounds(
                  handle, id, property, kDataBrowserPropertyEnclosingPart, rect)
              == OS.noErr) {
            int x = rect.left;
            int y = rect.top;
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            redrawWidget(handle, x, y, width, height, false);
          }
          return OS.noErr;
        }
      }
    }
    OS.memmove(rect, theRect, sizeof);
    int x = rect.left;
    int y = rect.top;
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    GC gc = paintGC;
    if (gc == null) {
      GCData data = new GCData();
      int[] port = new int[1];
      OS.GetPort(port);
      data.port = port[0];
      gc = GC.carbon_new(this, data);
    }
    OS.GetDataBrowserItemPartBounds(handle, id, property, kDataBrowserPropertyEnclosingPart, rect);
    int gridWidth = (getLinesVisible()) ? GRID_WIDTH : 0;
    int itemX = rect.left + gridWidth;
    int itemY = rect.top;
    int itemWidth = (rect.right - rect.left) - gridWidth;
    int itemHeight = (rect.bottom - rect.top) + 1;
    if (drawBackground) {
      drawBackground = false;
      Region region = new Region(display);
      Rectangle clientArea = getClientArea();
      int headerHeight = getHeaderHeight();
      clientArea.y += headerHeight;
      clientArea.height -= headerHeight;
      if (clientArea.height < 0) {
        clientArea.height = 0;
      }
      region.add(clientArea);
      if (((style & SWT.CHECK) != 0) || (gridWidth != 0)) {
        int rgn = OS.NewRgn();
        if ((style & SWT.CHECK) != 0) {
          if (OS.GetDataBrowserItemPartBounds(
                  handle, id, Table.CHECK_COLUMN_ID, kDataBrowserPropertyEnclosingPart, rect)
              == OS.noErr) {
            OS.SetRectRgn(
                rgn,
                ((short) (rect.left)),
                ((short) (clientArea.y)),
                ((short) (rect.right + gridWidth)),
                ((short) (clientArea.y + clientArea.height)));
            OS.DiffRgn(region.handle, rgn, region.handle);
          }
        }
        if (gridWidth != 0) {
          if (columnCount == 0) {
            if (OS.GetDataBrowserItemPartBounds(
                    handle, id, Table.COLUMN_ID, kDataBrowserPropertyEnclosingPart, rect)
                == OS.noErr) {
              OS.SetRectRgn(
                  rgn,
                  ((short) (rect.right)),
                  ((short) (clientArea.y)),
                  ((short) (rect.right + gridWidth)),
                  ((short) (clientArea.y + clientArea.height)));
              OS.DiffRgn(region.handle, rgn, region.handle);
            }
          } else {
            for (int i = 0; i < columnCount; i++) {
              if (OS.GetDataBrowserItemPartBounds(
                      handle, id, columns[i].id, kDataBrowserPropertyEnclosingPart, rect)
                  == OS.noErr) {
                OS.SetRectRgn(
                    rgn,
                    ((short) (rect.right)),
                    ((short) (clientArea.y)),
                    ((short) (rect.right + gridWidth)),
                    ((short) (clientArea.y + clientArea.height)));
                OS.DiffRgn(region.handle, rgn, region.handle);
              }
            }
          }
        }
        OS.DisposeRgn(rgn);
      }
      if (region != null) {
        gc.setClipping(region);
      }
      fillBackground(handle, gc.handle, null);
      if (region != null) {
        gc.setClipping(((Rectangle) (null)));
        region.dispose();
      }
    }
    OS.CGContextSaveGState(gc.handle);
    int itemRgn = OS.NewRgn();
    OS.SetRectRgn(
        itemRgn,
        ((short) (itemX)),
        ((short) (itemY)),
        ((short) (itemX + itemWidth)),
        ((short) (itemY + itemHeight)));
    int clip = OS.NewRgn();
    OS.GetClip(clip);
    OS.SectRgn(clip, itemRgn, itemRgn);
    OS.DisposeRgn(clip);
    Region region = Region.carbon_new(display, itemRgn);
    Font font = item.getFont(columnIndex);
    Color background = item.getBackground(columnIndex);
    Color foreground = item.getForeground(columnIndex);
    Image image = item.getImage(columnIndex);
    String text = item.getText(columnIndex);
    gc.setClipping(region);
    gc.setFont(font);
    Point extent = gc.stringExtent(text);
    int contentWidth = extent.x;
    Rectangle imageBounds = null;
    int gap = 0;
    if (image != null) {
      gap = getGap();
      imageBounds = image.getBounds();
      contentWidth += this.imageBounds.width + gap;
    }
    int paintWidth = contentWidth;
    if (hooks(MeasureItem)) {
      Event event = new Event();
      event.item = item;
      event.index = columnIndex;
      event.gc = gc;
      event.width = contentWidth;
      event.height = itemHeight;
      sendEvent(MeasureItem, event);
      if (this.itemHeight < event.height) {
        this.itemHeight = event.height;
        OS.SetDataBrowserTableViewRowHeight(handle, ((short) (event.height)));
        redrawWidget(handle, false);
      }
      if (setScrollWidth(item)) {
        redrawWidget(handle, false);
      }
      contentWidth = event.width;
      itemHeight = event.height;
      gc.setClipping(region);
      gc.setFont(font);
    }
    int drawState = SWT.FOREGROUND;
    if ((item.background != null)
        || ((item.cellBackground != null) && (item.cellBackground[columnIndex] != null))) {
      drawState |= SWT.BACKGROUND;
    }
    if ((itemState & (OS.kDataBrowserItemIsSelected | OS.kDataBrowserItemIsDragTarget)) != 0) {
      drawState |= SWT.SELECTED;
    }
    boolean wasSelected = (drawState & SWT.SELECTED) != 0;
    if (((drawState & SWT.SELECTED) != 0)
        && (((style & SWT.FULL_SELECTION) != 0) || (columnIndex == 0))) {
      gc.setBackground(display.getSystemColor(COLOR_LIST_SELECTION));
      gc.setForeground(display.getSystemColor(COLOR_LIST_SELECTION_TEXT));
    } else {
      gc.setBackground(background);
      gc.setForeground(foreground);
    }
    if (hooks(EraseItem)) {
      Event event = new Event();
      event.item = item;
      event.index = columnIndex;
      event.gc = gc;
      event.x = itemX;
      event.y = itemY;
      event.width = itemWidth;
      event.height = itemHeight;
      event.detail = drawState;
      sendEvent(EraseItem, event);
      drawState = (event.doit) ? event.detail : 0;
      gc.setClipping(region);
      gc.setFont(font);
      if (((drawState & SWT.SELECTED) != 0)
          && (((style & SWT.FULL_SELECTION) != 0) || (columnIndex == 0))) {
        gc.setBackground(display.getSystemColor(COLOR_LIST_SELECTION));
        gc.setForeground(display.getSystemColor(COLOR_LIST_SELECTION_TEXT));
      } else {
        gc.setBackground(background);
        if (!wasSelected) {
          gc.setForeground(foreground);
        }
      }
    }
    if (columnCount != 0) {
      TableColumn column = columns[columnIndex];
      if ((column.style & SWT.CENTER) != 0) {
        x += (width - contentWidth) / 2;
      }
      if ((column.style & SWT.RIGHT) != 0) {
        x += width - contentWidth;
      }
    }
    int stringX = x;
    if (image != null) {
      stringX += this.imageBounds.width + gap;
    }
    if (((drawState & SWT.SELECTED) != 0)
        && (((style & SWT.FULL_SELECTION) != 0) || (columnIndex == 0))) {
      if (((style & SWT.HIDE_SELECTION) == 0) || hasFocus()) {
        if ((style & SWT.FULL_SELECTION) != 0) {
          gc.fillRectangle(itemX, itemY, itemWidth, itemHeight - 1);
          drawState &= ~SWT.BACKGROUND;
        } else if (columnIndex == 0) {
          gc.fillRectangle(stringX - 1, y, extent.x + 2, itemHeight - 1);
          drawState &= ~SWT.BACKGROUND;
        }
      } else if ((drawState & SWT.BACKGROUND) != 0) {
        gc.setBackground(background);
      }
    }
    if ((drawState & SWT.BACKGROUND) != 0) {
      if (columnCount == 0) {
        gc.fillRectangle(stringX - 1, y, extent.x + 2, itemHeight - 1);
      } else {
        gc.fillRectangle(itemX, itemY, itemWidth, itemHeight);
      }
    }
    if ((drawState & SWT.FOREGROUND) != 0) {
      if (image != null) {
        int imageX = x;
        int imageY = y + ((height - this.imageBounds.height) / 2);
        gc.drawImage(
            image,
            0,
            0,
            imageBounds.width,
            imageBounds.height,
            imageX,
            imageY,
            this.imageBounds.width,
            this.imageBounds.height);
      }
      gc.drawString(text, stringX, y + ((height - extent.y) / 2), true);
    }
    if (hooks(PaintItem)) {
      Event event = new Event();
      event.item = item;
      event.index = columnIndex;
      event.gc = gc;
      event.x = x;
      event.y = y;
      event.width = paintWidth;
      event.height = itemHeight;
      event.detail = drawState;
      sendEvent(PaintItem, event);
    }
    OS.CGContextRestoreGState(gc.handle);
    OS.DisposeRgn(itemRgn);
    if (gc != paintGC) {
      gc.dispose();
    }
    return OS.noErr;
  }
}

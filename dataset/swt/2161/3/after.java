class PlaceHold {
  boolean setItemSize(GC gc) {
    boolean changed = false;
    if (isDisposed()) {
      return changed;
    }
    Point size = getSize();
    if ((size.x <= 0) || (size.y <= 0)) {
      return changed;
    }
    Rectangle trim = renderer.computeTrim(PART_BORDER, NONE, 0, 0, 0, 0);
    int borderRight = trim.width + trim.x;
    int borderLeft = -trim.x;
    showChevron = false;
    if (single) {
      showChevron = true;
      if (selectedIndex != (-1)) {
        CTabItem tab = items[selectedIndex];
        int width = renderer.computeSize(selectedIndex, SELECTED, gc, DEFAULT, DEFAULT).x;
        width = Math.min(width, getRightItemEdge(gc) - borderLeft);
        if ((tab.height != tabHeight) || (tab.width != width)) {
          changed = true;
          tab.shortenedText = null;
          tab.shortenedTextWidth = 0;
          tab.height = tabHeight;
          tab.width = width;
          tab.closeRect.width = tab.closeRect.height = 0;
          if (showClose || tab.showClose) {
            Point closeSize =
                renderer.computeSize(PART_CLOSE_BUTTON, SELECTED, gc, DEFAULT, DEFAULT);
            tab.closeRect.width = closeSize.x;
            tab.closeRect.height = closeSize.y;
          }
        }
      }
      return changed;
    }
    if (items.length == 0) {
      return changed;
    }
    int[] widths;
    int tabAreaWidth = ((size.x - borderLeft) - borderRight) - 3;
    if (showMin) {
      tabAreaWidth -= renderer.computeSize(PART_MIN_BUTTON, NONE, gc, DEFAULT, DEFAULT).x;
    }
    if (showMax) {
      tabAreaWidth -= renderer.computeSize(PART_MAX_BUTTON, NONE, gc, DEFAULT, DEFAULT).x;
    }
    if ((topRightAlignment == SWT.RIGHT) && (topRight != null)) {
      Point rightSize = topRight.computeSize(DEFAULT, DEFAULT, false);
      tabAreaWidth -= rightSize.x + 3;
    }
    tabAreaWidth = Math.max(0, tabAreaWidth);
    int minWidth = 0;
    int[] minWidths = new int[items.length];
    for (int i = 0; i < priority.length; i++) {
      int index = priority[i];
      int state = CTabFolderRenderer.MINIMUM_SIZE;
      if (index == selectedIndex) {
        state |= SWT.SELECTED;
      }
      minWidths[index] = renderer.computeSize(index, state, gc, DEFAULT, DEFAULT).x;
      minWidth += minWidths[index];
      if (minWidth > tabAreaWidth) {
        break;
      }
    }
    if (minWidth > tabAreaWidth) {
      showChevron = items.length > 1;
      if (showChevron) {
        tabAreaWidth -= renderer.computeSize(PART_CHEVRON_BUTTON, NONE, gc, DEFAULT, DEFAULT).x;
      }
      widths = minWidths;
      int index = (selectedIndex != (-1)) ? selectedIndex : 0;
      if (tabAreaWidth < widths[index]) {
        widths[index] = Math.max(0, tabAreaWidth);
      }
    } else {
      int maxWidth = 0;
      int[] maxWidths = new int[items.length];
      for (int i = 0; i < items.length; i++) {
        int state = 0;
        if (i == selectedIndex) {
          state |= SWT.SELECTED;
        }
        maxWidths[i] = renderer.computeSize(i, state, gc, DEFAULT, DEFAULT).x;
        maxWidth += maxWidths[i];
      }
      if (maxWidth <= tabAreaWidth) {
        widths = maxWidths;
      } else {
        int extra = (tabAreaWidth - minWidth) / items.length;
        while (true) {
          int large = 0;
          int totalWidth = 0;
          for (int i = 0; i < items.length; i++) {
            if (maxWidths[i] > (minWidths[i] + extra)) {
              totalWidth += minWidths[i] + extra;
              large++;
            } else {
              totalWidth += maxWidths[i];
            }
          }
          if (totalWidth >= tabAreaWidth) {
            extra--;
            break;
          }
          if ((large == 0) || ((tabAreaWidth - totalWidth) < large)) {
            break;
          }
          extra++;
        }
        widths = new int[items.length];
        for (int i = 0; i < items.length; i++) {
          widths[i] = Math.min(maxWidths[i], minWidths[i] + extra);
        }
      }
    }
    for (int i = 0; i < items.length; i++) {
      CTabItem tab = items[i];
      int width = widths[i];
      if ((tab.height != tabHeight) || (tab.width != width)) {
        changed = true;
        tab.shortenedText = null;
        tab.shortenedTextWidth = 0;
        tab.height = tabHeight;
        tab.width = width;
        tab.closeRect.width = tab.closeRect.height = 0;
        if (showClose || tab.showClose) {
          if ((i == selectedIndex) || showUnselectedClose) {
            Point closeSize = renderer.computeSize(PART_CLOSE_BUTTON, NONE, gc, DEFAULT, DEFAULT);
            tab.closeRect.width = closeSize.x;
            tab.closeRect.height = closeSize.y;
          }
        }
      }
    }
    return changed;
  }
}

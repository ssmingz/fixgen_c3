class PlaceHold {
  boolean updateItems(int showIndex) {
    GC gc = new GC(this);
    if (((!single) && (!mru)) && (showIndex != (-1))) {
      int firstIndex = showIndex;
      if (priority[0] < showIndex) {
        Rectangle trim = renderer.computeTrim(PART_HEADER, NONE, 0, 0, 0, 0);
        int borderLeft = -trim.x;
        int maxWidth = getRightItemEdge(gc) - borderLeft;
        int width = 0;
        int[] widths = new int[items.length];
        for (int i = priority[0]; i <= showIndex; i++) {
          int state = CTabFolderRenderer.MINIMUM_SIZE;
          if (i == selectedIndex) {
            state |= SWT.SELECTED;
          }
          widths[i] = renderer.computeSize(i, state, gc, DEFAULT, DEFAULT).x;
          width += widths[i];
          if (width > maxWidth) {
            break;
          }
        }
        if (width > maxWidth) {
          width = 0;
          for (int i = showIndex; i >= 0; i--) {
            int state = CTabFolderRenderer.MINIMUM_SIZE;
            if (i == selectedIndex) {
              state |= SWT.SELECTED;
            }
            if (widths[i] == 0) {
              widths[i] = renderer.computeSize(i, state, gc, DEFAULT, DEFAULT).x;
            }
            width += widths[i];
            if (width > maxWidth) {
              break;
            }
            firstIndex = i;
          }
        } else {
          firstIndex = priority[0];
          for (int i = showIndex + 1; i < items.length; i++) {
            int state = CTabFolderRenderer.MINIMUM_SIZE;
            if (i == selectedIndex) {
              state |= SWT.SELECTED;
            }
            widths[i] = renderer.computeSize(i, state, gc, DEFAULT, DEFAULT).x;
            width += widths[i];
            if (width >= maxWidth) {
              break;
            }
          }
          if (width < maxWidth) {
            for (int i = priority[0] - 1; i >= 0; i--) {
              int state = CTabFolderRenderer.MINIMUM_SIZE;
              if (i == selectedIndex) {
                state |= SWT.SELECTED;
              }
              if (widths[i] == 0) {
                widths[i] = renderer.computeSize(i, state, gc, DEFAULT, DEFAULT).x;
              }
              width += widths[i];
              if (width > maxWidth) {
                break;
              }
              firstIndex = i;
            }
          }
        }
      }
      if (firstIndex != priority[0]) {
        int index = 0;
        for (int i = firstIndex; i < items.length; i++) {
          priority[index++] = i;
        }
        for (int i = 0; i < firstIndex; i++) {
          priority[index++] = i;
        }
      }
    }
    boolean oldShowChevron = showChevron;
    boolean changed = setItemSize(gc);
    changed |= setItemLocation(gc);
    setButtonBounds(gc);
    changed |= showChevron != oldShowChevron;
    if (changed && (getToolTipText() != null)) {
      Point pt = getDisplay().getCursorLocation();
      pt = toControl(pt);
      _setToolTipText(pt.x, pt.y);
    }
    gc.dispose();
    return changed;
  }
}

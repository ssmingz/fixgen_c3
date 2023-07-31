class PlaceHold {
  protected Point computeSize(int part, int state, GC gc, int wHint, int hHint) {
    int width = 0;
    int height = 0;
    switch (part) {
      case PART_HEADER:
        if (parent.fixedTabHeight != SWT.DEFAULT) {
          height = (parent.fixedTabHeight == 0) ? 0 : parent.fixedTabHeight + 1;
        } else {
          CTabItem[] items = parent.items;
          if (items.length == 0) {
            height = (gc.textExtent("Default", FLAGS).y + ITEM_TOP_MARGIN) + ITEM_BOTTOM_MARGIN;
          } else {
            for (int i = 0; i < items.length; i++) {
              height = Math.max(height, computeSize(i, NONE, gc, wHint, hHint).y);
            }
          }
          gc.dispose();
        }
        break;
      case PART_MAX_BUTTON:
      case PART_MIN_BUTTON:
      case PART_CLOSE_BUTTON:
        width = height = BUTTON_SIZE;
        break;
      case PART_CHEVRON_BUTTON:
        width = (3 * BUTTON_SIZE) / 2;
        height = BUTTON_SIZE;
        break;
      default:
        if ((0 <= part) && (part < parent.getItemCount())) {
          updateCurves();
          CTabItem item = parent.items[part];
          if (item.isDisposed()) {
            return new Point(0, 0);
          }
          Image image = item.getImage();
          if ((image != null) && (!image.isDisposed())) {
            Rectangle bounds = image.getBounds();
            if (((state & SWT.SELECTED) != 0) || parent.showUnselectedImage) {
              width += bounds.width;
            }
            height = bounds.height;
          }
          String text = null;
          if ((state & MINIMUM_SIZE) != 0) {
            int minChars = parent.minChars;
            text = (minChars == 0) ? null : item.getText();
            if ((text != null) && (text.length() > minChars)) {
              if (useEllipses()) {
                int end =
                    (minChars < (ELLIPSIS.length() + 1)) ? minChars : minChars - ELLIPSIS.length();
                text = text.substring(0, end);
                if (minChars > (ELLIPSIS.length() + 1)) {
                  text += ELLIPSIS;
                }
              } else {
                int end = minChars;
                text = text.substring(0, end);
              }
            }
          } else {
            text = item.getText();
          }
          if (text != null) {
            if (width > 0) {
              width += INTERNAL_SPACING;
            }
            if (item.font == null) {
              Point size = gc.textExtent(text, FLAGS);
              width += size.x;
              height = Math.max(height, size.y);
            } else {
              Font gcFont = gc.getFont();
              gc.setFont(item.font);
              Point size = gc.textExtent(text, FLAGS);
              width += size.x;
              height = Math.max(height, size.y);
              gc.setFont(gcFont);
            }
          }
          if (parent.showClose || item.showClose) {
            if (((state & SWT.SELECTED) != 0) || parent.showUnselectedClose) {
              if (width > 0) {
                width += INTERNAL_SPACING;
              }
              width += computeSize(PART_CLOSE_BUTTON, NONE, gc, DEFAULT, DEFAULT).x;
            }
          }
        }
        break;
    }
    Rectangle trim = computeTrim(part, state, 0, 0, width, height);
    width = trim.width;
    height = trim.height;
    return new Point(width, height);
  }
}

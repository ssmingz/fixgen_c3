class PlaceHold {
  void resizeRectangles(int xChange, int yChange) {
    if (bounds == null) {
      return;
    }
    if ((parent != null) && ((parent.style & SWT.MIRRORED) != 0)) {
      xChange *= -1;
    }
    if (((xChange < 0) && ((style & SWT.LEFT) != 0)) && ((cursorOrientation & SWT.RIGHT) == 0)) {
      cursorOrientation |= SWT.LEFT;
    }
    if (((xChange > 0) && ((style & SWT.RIGHT) != 0)) && ((cursorOrientation & SWT.LEFT) == 0)) {
      cursorOrientation |= SWT.RIGHT;
    }
    if (((yChange < 0) && ((style & SWT.UP) != 0)) && ((cursorOrientation & SWT.DOWN) == 0)) {
      cursorOrientation |= SWT.UP;
    }
    if (((yChange > 0) && ((style & SWT.DOWN) != 0)) && ((cursorOrientation & SWT.UP) == 0)) {
      cursorOrientation |= SWT.DOWN;
    }
    if ((cursorOrientation & SWT.LEFT) != 0) {
      if (xChange > bounds.width) {
        if ((style & SWT.RIGHT) == 0) {
          return;
        }
        cursorOrientation |= SWT.RIGHT;
        cursorOrientation &= ~SWT.LEFT;
        bounds.x += bounds.width;
        xChange -= bounds.width;
        bounds.width = 0;
        if (proportions.length > 1) {
          for (int i = 0; i < proportions.length; i++) {
            Rectangle proportion = proportions[i];
            proportion.x = (100 - proportion.x) - proportion.width;
          }
        }
      }
    } else if ((cursorOrientation & SWT.RIGHT) != 0) {
      if (bounds.width < (-xChange)) {
        if ((style & SWT.LEFT) == 0) {
          return;
        }
        cursorOrientation |= SWT.LEFT;
        cursorOrientation &= ~SWT.RIGHT;
        xChange += bounds.width;
        bounds.width = 0;
        if (proportions.length > 1) {
          for (int i = 0; i < proportions.length; i++) {
            Rectangle proportion = proportions[i];
            proportion.x = (100 - proportion.x) - proportion.width;
          }
        }
      }
    }
    if ((cursorOrientation & SWT.UP) != 0) {
      if (yChange > bounds.height) {
        if ((style & SWT.DOWN) == 0) {
          return;
        }
        cursorOrientation |= SWT.DOWN;
        cursorOrientation &= ~SWT.UP;
        bounds.y += bounds.height;
        yChange -= bounds.height;
        bounds.height = 0;
        if (proportions.length > 1) {
          for (int i = 0; i < proportions.length; i++) {
            Rectangle proportion = proportions[i];
            proportion.y = (100 - proportion.y) - proportion.height;
          }
        }
      }
    } else if ((cursorOrientation & SWT.DOWN) != 0) {
      if (bounds.height < (-yChange)) {
        if ((style & SWT.UP) == 0) {
          return;
        }
        cursorOrientation |= SWT.UP;
        cursorOrientation &= ~SWT.DOWN;
        yChange += bounds.height;
        bounds.height = 0;
        if (proportions.length > 1) {
          for (int i = 0; i < proportions.length; i++) {
            Rectangle proportion = proportions[i];
            proportion.y = (100 - proportion.y) - proportion.height;
          }
        }
      }
    }
    if ((cursorOrientation & SWT.LEFT) != 0) {
      bounds.x += xChange;
      bounds.width -= xChange;
    } else if ((cursorOrientation & SWT.RIGHT) != 0) {
      bounds.width += xChange;
    }
    if ((cursorOrientation & SWT.UP) != 0) {
      bounds.y += yChange;
      bounds.height -= yChange;
    } else if ((cursorOrientation & SWT.DOWN) != 0) {
      bounds.height += yChange;
    }
    Rectangle[] newRects = new Rectangle[rectangles.length];
    for (int i = 0; i < rectangles.length; i++) {
      Rectangle proportion = proportions[i];
      newRects[i] =
          new Rectangle(
              ((proportion.x * bounds.width) / 100) + bounds.x,
              ((proportion.y * bounds.height) / 100) + bounds.y,
              (proportion.width * bounds.width) / 100,
              (proportion.height * bounds.height) / 100);
    }
    rectangles = newRects;
  }
}

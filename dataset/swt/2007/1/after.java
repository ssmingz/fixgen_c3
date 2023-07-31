class PlaceHold {
  public void drawText(String string, int x, int y, int flags) {
    if (handle == null) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    NSAutoreleasePool pool = checkGC(((CLIPPING | TRANSFORM) | FONT) | FOREGROUND_FILL);
    try {
      int length = string.length();
      if (length == 0) {
        return;
      }
      boolean mode = true;
      switch (data.textAntialias) {
        case SWT.DEFAULT:
          if (!handle.isDrawingToScreen()) {
            mode = false;
          }
          break;
        case SWT.OFF:
          mode = false;
          break;
        case SWT.ON:
          mode = true;
          break;
      }
      handle.saveGraphicsState();
      handle.setShouldAntialias(mode);
      if (data.textStorage == null) {
        createLayout();
      }
      NSAttributedString attribStr = createString(string, flags, true);
      data.textStorage.setAttributedString(attribStr);
      attribStr.release();
      NSPoint pt = new NSPoint();
      pt.x = x;
      pt.y = y;
      NSRange range = data.layoutManager.glyphRangeForTextContainer(data.textContainer);
      if ((flags & SWT.DRAW_TRANSPARENT) == 0) {
        NSRect rect = data.layoutManager.usedRectForTextContainer(data.textContainer);
        rect.x = x;
        rect.y = y;
        Pattern pattern = data.backgroundPattern;
        if (pattern != null) {
          setPatternPhase(pattern);
        }
        if ((pattern != null) && (pattern.gradient != null)) {
          NSBezierPath path = NSBezierPath.bezierPathWithRect(rect);
          fillPattern(path, pattern);
        } else {
          NSColor bg = data.bg;
          if (bg == null) {
            double[] color = data.background;
            bg =
                data.bg =
                    NSColor.colorWithDeviceRed(color[0], color[1], color[2], data.alpha / 255.0F);
            bg.retain();
          }
          bg.setFill();
          NSBezierPath.fillRect(rect);
        }
      }
      data.layoutManager.drawGlyphsForGlyphRange(range, pt);
      handle.restoreGraphicsState();
    } finally {
      uncheckGC(pool);
    }
  }
}

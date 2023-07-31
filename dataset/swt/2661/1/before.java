class PlaceHold {
  public void dragOver(DropTargetEvent event) {
    if (!checkWidget(event)) {
      return;
    }
    int effect = event.feedback;
    DropTarget dt = ((DropTarget) (event.widget));
    StyledText text = ((StyledText) (dt.getControl()));
    Point pt = text.getDisplay().map(null, text, event.x, event.y);
    if ((effect & DND.FEEDBACK_SCROLL) == 0) {
      scrollBeginTime = 0;
      scrollX = scrollY = -1;
    } else if (text.getCharCount() == 0) {
      scrollBeginTime = 0;
      scrollX = scrollY = -1;
    } else if ((((scrollX != (-1)) && (scrollY != (-1))) && (scrollBeginTime != 0))
        && (((pt.x >= scrollX) && (pt.x <= (scrollX + SCROLL_TOLERANCE)))
            || ((pt.y >= scrollY) && (pt.y <= (scrollY + SCROLL_TOLERANCE))))) {
      if (System.currentTimeMillis() >= scrollBeginTime) {
        Rectangle area = text.getClientArea();
        Rectangle bounds = text.getTextBounds(0, 0);
        int charWidth = bounds.width;
        int scrollAmount = 10 * charWidth;
        if (pt.x < (area.x + (3 * charWidth))) {
          int leftPixel = text.getHorizontalPixel();
          text.setHorizontalPixel(leftPixel - scrollAmount);
          if (text.getHorizontalPixel() != leftPixel) {
            text.redraw();
          }
        }
        if (pt.x > (area.width - (3 * charWidth))) {
          int leftPixel = text.getHorizontalPixel();
          text.setHorizontalPixel(leftPixel + scrollAmount);
          if (text.getHorizontalPixel() != leftPixel) {
            text.redraw();
          }
        }
        int lineHeight = bounds.height;
        if (pt.y < (area.y + lineHeight)) {
          int topPixel = text.getTopPixel();
          text.setTopPixel(topPixel - lineHeight);
          if (text.getTopPixel() != topPixel) {
            text.redraw();
          }
        }
        if (pt.y > (area.height - lineHeight)) {
          int topPixel = text.getTopPixel();
          text.setTopPixel(topPixel + lineHeight);
          if (text.getTopPixel() != topPixel) {
            text.redraw();
          }
        }
        scrollBeginTime = 0;
        scrollX = scrollY = -1;
      }
    } else {
      scrollBeginTime = System.currentTimeMillis() + SCROLL_HYSTERESIS;
      scrollX = pt.x;
      scrollY = pt.y;
    }
    if ((effect & DND.FEEDBACK_SELECT) != 0) {
      StyledTextContent content = text.getContent();
      int oldOffset = text.getCaretOffset();
      int newOffset = -1;
      try {
        newOffset = text.getOffsetAtLocation(pt);
      } catch (IllegalArgumentException ex1) {
        int maxOffset = content.getCharCount();
        Point maxLocation = text.getLocationAtOffset(maxOffset);
        if (pt.y >= maxLocation.y) {
          try {
            newOffset = text.getOffsetAtLocation(new Point(pt.x, maxLocation.y));
          } catch (IllegalArgumentException ex2) {
            newOffset = maxOffset;
          }
        } else {
          try {
            int startOffset = text.getOffsetAtLocation(new Point(0, pt.y));
            int endOffset = maxOffset;
            int line = content.getLineAtOffset(startOffset);
            int lineCount = content.getLineCount();
            if ((line + 1) < lineCount) {
              endOffset = content.getOffsetAtLine(line + 1) - 1;
            }
            int lineHeight = text.getLineHeight(startOffset);
            for (int i = endOffset; i >= startOffset; i--) {
              Point p = text.getLocationAtOffset(i);
              if (((p.x < pt.x) && (p.y < pt.y)) && ((p.y + lineHeight) > pt.y)) {
                newOffset = i;
                break;
              }
            }
          } catch (IllegalArgumentException ex2) {
            newOffset = -1;
          }
        }
      }
      if ((newOffset != (-1)) && (newOffset != oldOffset)) {
        int line = content.getLineAtOffset(newOffset);
        int lineOffset = content.getOffsetAtLine(line);
        int offsetInLine = newOffset - lineOffset;
        if (offsetInLine > content.getLine(line).length()) {
          newOffset = Math.max(0, newOffset - 1);
        }
        drawCaret(text, currentOffset, newOffset);
        currentOffset = newOffset;
      }
    }
  }
}

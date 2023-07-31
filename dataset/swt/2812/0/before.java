class PlaceHold {
  int getOffsetAtX(String line, int lineOffset, int lineXOffset) {
    int offset;
    GC gc = new GC(this);
    StyleRange[] styles = null;
    StyledTextEvent event = getLineStyleData(lineOffset, line);
    if (event != null) {
      styles = filterLineStyles(event.styles);
    }
    lineXOffset += horizontalScrollOffset;
    if (isBidi()) {
      int[] boldStyles = getBoldRanges(styles, lineOffset, line.length());
      StyledTextBidi bidi =
          new StyledTextBidi(
              gc, tabWidth, line, boldStyles, boldFont, getStyleOffsets(line, lineOffset));
      offset = bidi.getOffsetAtX(lineXOffset);
    } else {
      int low = -1;
      int high = line.length();
      while ((high - low) > 1) {
        offset = (high + low) / 2;
        if (lineXOffset < textWidth(line, lineOffset, 0, offset + 1, styles, 0, gc, null)) {
          high = offset;
        } else if ((high == line.length()) && ((high - offset) == 1)) {
          high = -1;
        } else {
          low = offset;
        }
      }
      offset = high;
    }
    gc.dispose();
    return offset;
  }
}

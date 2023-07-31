class PlaceHold {
  int getXAtOffset(String line, int lineIndex, int lineOffset) {
    int x;
    if ((lineOffset == 0) && (isBidi() == false)) {
      x = leftMargin;
    } else {
      GC gc = new GC(this);
      x = getTextPosition(line, lineIndex, Math.min(line.length(), lineOffset), gc) + leftMargin;
      gc.dispose();
      if (lineOffset > line.length()) {
        x += lineEndSpaceWidth;
      }
    }
    return x - horizontalScrollOffset;
  }
}

class PlaceHold {
  int getXAtOffset(String line, int lineIndex, int offsetInLine) {
    int x = 0;
    int lineLength = line.length();
    if (lineIndex < (content.getLineCount() - 1)) {
      int endLineOffset = content.getOffsetAtLine(lineIndex + 1) - 1;
      if ((lineLength < offsetInLine) && (offsetInLine <= endLineOffset)) {
        offsetInLine = lineLength;
      }
    }
    if ((lineLength != 0) && (offsetInLine <= lineLength)) {
      int lineOffset = content.getOffsetAtLine(lineIndex);
      TextLayout layout = renderer.getTextLayout(line, lineOffset);
      if ((!advancing) || (offsetInLine == 0)) {
        x = layout.getLocation(offsetInLine, false).x;
      } else {
        x = layout.getLocation(offsetInLine - 1, true).x;
      }
      renderer.disposeTextLayout(layout);
    }
    return (x + leftMargin) - horizontalScrollOffset;
  }
}

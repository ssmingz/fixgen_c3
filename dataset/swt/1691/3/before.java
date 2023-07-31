class PlaceHold {
  int getCaretDirection() {
    if (!isBidi()) {
      return SWT.DEFAULT;
    }
    if ((!updateCaretDirection) && (caretDirection != SWT.NULL)) {
      return caretDirection;
    }
    updateCaretDirection = false;
    int caretLine = getCaretLine();
    int lineOffset = content.getOffsetAtLine(caretLine);
    String line = content.getLine(caretLine);
    int offset = caretOffset - lineOffset;
    int lineLength = line.length();
    if (lineLength == 0) {
      return isMirrored() ? SWT.RIGHT : SWT.LEFT;
    }
    if (advancing && (offset > 0)) {
      offset--;
    }
    if ((offset == lineLength) && (offset > 0)) {
      offset--;
    }
    while ((offset > 0) && Character.isDigit(line.charAt(offset))) {
      offset--;
    }
    if ((offset == 0) && Character.isDigit(line.charAt(offset))) {
      return isMirrored() ? SWT.RIGHT : SWT.LEFT;
    }
    TextLayout layout = renderer.getTextLayout(line, lineOffset);
    int level = layout.getLevel(offset);
    renderer.disposeTextLayout(layout);
    return (level & 1) != 0 ? SWT.RIGHT : SWT.LEFT;
  }
}

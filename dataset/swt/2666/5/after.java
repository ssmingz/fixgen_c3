class PlaceHold {
  void doPageStart() {
    int topOffset;
    if (wordWrap || visualWrap) {
      int y;
      int lineIndex;
      if (topIndexY > 0) {
        lineIndex = topIndex - 1;
        y = renderer.getLineHeight(lineIndex) - topIndexY;
      } else {
        lineIndex = topIndex;
        y = -topIndexY;
      }
      TextLayout layout = renderer.getTextLayout(lineIndex);
      int index = 0;
      int lineCount = layout.getLineCount();
      while (index < lineCount) {
        Rectangle bounds = layout.getLineBounds(index);
        if (y <= bounds.y) {
          break;
        }
        index++;
      }
      if (index == lineCount) {
        topOffset = content.getOffsetAtLine(lineIndex + 1);
      } else {
        topOffset = content.getOffsetAtLine(lineIndex) + layout.getLineOffsets()[index];
      }
      renderer.disposeTextLayout(layout);
    } else {
      topOffset = content.getOffsetAtLine(topIndex);
    }
    if (caretOffset > topOffset) {
      setCaretOffset(topOffset, OFFSET_LEADING);
      showCaret();
    }
  }
}

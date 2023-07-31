class PlaceHold {
  void doPageEnd() {
    if (isSingleLine()) {
      doLineEnd();
    } else {
      int bottomOffset;
      if (wordWrap || visualWrap) {
        int lineIndex = getPartialBottomIndex();
        TextLayout layout = renderer.getTextLayout(lineIndex);
        int y = (clientAreaHeight - bottomMargin) - getLinePixel(lineIndex);
        int index = layout.getLineCount() - 1;
        while (index >= 0) {
          Rectangle bounds = layout.getLineBounds(index);
          if (y >= (bounds.y + bounds.height)) {
            break;
          }
          index--;
        }
        if ((index == (-1)) && (lineIndex > 0)) {
          bottomOffset =
              content.getOffsetAtLine(lineIndex - 1) + content.getLine(lineIndex - 1).length();
        } else {
          bottomOffset =
              content.getOffsetAtLine(lineIndex)
                  + Math.max(0, layout.getLineOffsets()[index + 1] - 1);
        }
        renderer.disposeTextLayout(layout);
      } else {
        int lineIndex = getBottomIndex();
        bottomOffset = content.getOffsetAtLine(lineIndex) + content.getLine(lineIndex).length();
      }
      if (caretOffset < bottomOffset) {
        setCaretOffset(bottomOffset, OFFSET_LEADING);
        showCaret();
      }
    }
  }
}

class PlaceHold {
  void doLineEnd() {
    int caretLine = getCaretLine();
    int lineOffset = content.getOffsetAtLine(caretLine);
    int lineEndOffset;
    if (wordWrap) {
      int offsetInLine = caretOffset - lineOffset;
      TextLayout layout = renderer.getTextLayout(caretLine);
      int lineIndex = layout.getLineIndex(offsetInLine);
      int[] offsets = layout.getLineOffsets();
      if (((lineIndex != 0) && (offsetInLine == offsets[lineIndex]))
          && (caretAlignment == PREVIOUS_OFFSET_TRAILING)) {
        lineIndex--;
      }
      renderer.disposeTextLayout(layout);
      lineEndOffset = lineOffset + offsets[lineIndex + 1];
    } else {
      int lineLength = content.getLine(caretLine).length();
      lineEndOffset = lineOffset + lineLength;
    }
    if (caretOffset < lineEndOffset) {
      caretOffset = lineEndOffset;
      caretAlignment = PREVIOUS_OFFSET_TRAILING;
      showCaret();
    }
  }
}

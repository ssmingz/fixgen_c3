class PlaceHold {
  void doLineStart() {
    int caretLine = getCaretLine();
    int lineOffset = content.getOffsetAtLine(caretLine);
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
      lineOffset += offsets[lineIndex];
    }
    if (caretOffset > lineOffset) {
      caretOffset = lineOffset;
      caretAlignment = OFFSET_LEADING;
      showCaret();
    }
  }
}

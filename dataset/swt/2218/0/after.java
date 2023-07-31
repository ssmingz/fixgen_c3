class PlaceHold {
  void doLineEnd() {
    int caretLine = getCaretLine();
    int lineOffset = content.getOffsetAtLine(caretLine);
    int lineEndOffset;
    if (wordWrap) {
      TextLayout layout = renderer.getTextLayout(caretLine);
      int offsetInLine = caretOffset - lineOffset;
      int lineIndex = getVisualLineIndex(layout, offsetInLine);
      int[] offsets = layout.getLineOffsets();
      lineEndOffset = lineOffset + offsets[lineIndex + 1];
      renderer.disposeTextLayout(layout);
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

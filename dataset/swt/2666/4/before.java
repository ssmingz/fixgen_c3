class PlaceHold {
  void doLineStart() {
    int caretLine = getCaretLine();
    int lineOffset = content.getOffsetAtLine(caretLine);
    if (wordWrap) {
      TextLayout layout = renderer.getTextLayout(caretLine);
      int offsetInLine = caretOffset - lineOffset;
      int lineIndex = getVisualLineIndex(layout, offsetInLine);
      int[] offsets = layout.getLineOffsets();
      lineOffset += offsets[lineIndex];
      renderer.disposeTextLayout(layout);
    }
    setCaretOffset(lineOffset, OFFSET_LEADING);
    showCaret();
  }
}

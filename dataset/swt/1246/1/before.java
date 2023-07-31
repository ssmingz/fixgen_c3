class PlaceHold {
  void setBidiKeyboardLanguage() {
    int caretLine = getCaretLine();
    int lineStartOffset = content.getOffsetAtLine(caretLine);
    int offsetInLine = caretOffset - lineStartOffset;
    String lineText = content.getLine(caretLine);
    GC gc = new GC(this);
    StyledTextBidi bidi;
    int lineLength = lineText.length();
    bidi = new StyledTextBidi(gc, lineText, getBidiSegments(lineStartOffset, lineText));
    if (offsetInLine == 0) {
      bidi.setKeyboardLanguage(offsetInLine);
    } else if (offsetInLine >= lineLength) {
      offsetInLine = Math.min(offsetInLine, lineLength - 1);
      bidi.setKeyboardLanguage(offsetInLine);
    } else if (lastCaretDirection == ST.COLUMN_NEXT) {
      bidi.setKeyboardLanguage(offsetInLine - 1);
    } else {
      bidi.setKeyboardLanguage(offsetInLine);
    }
    gc.dispose();
  }
}

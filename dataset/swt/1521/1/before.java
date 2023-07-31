class PlaceHold {
  void setCaretLocation() {
    if (isBidi()) {
      setBidiCaretLocation(null);
    } else {
      Caret caret = getCaret();
      int caretLine = getCaretLine();
      int lineStartOffset = content.getOffsetAtLine(caretLine);
      columnX = getXAtOffset(content.getLine(caretLine), caretLine, caretOffset - lineStartOffset);
      if (caret != null) {
        caret.setLocation(columnX, ((caretLine * lineHeight) - verticalScrollOffset) + topMargin);
      }
    }
  }
}

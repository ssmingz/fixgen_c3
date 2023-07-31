class PlaceHold {
  void setCaretLocation() {
    if (isBidi()) {
      setBidiCaretLocation(null);
    } else {
      Caret caret = getCaret();
      if (caret != null) {
        int lineStartOffset = content.getOffsetAtLine(caretLine);
        int caretX =
            getXAtOffset(content.getLine(caretLine), caretLine, caretOffset - lineStartOffset);
        caret.setLocation(
            caretX + leftMargin, ((caretLine * lineHeight) - verticalScrollOffset) + topMargin);
      }
    }
  }
}

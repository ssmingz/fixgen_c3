class PlaceHold {
  private void _uncommentLine() {
    try {
      int curCol = getCurrentCol();
      int lineStart = getCurrentLocation() - curCol;
      String text = getText(lineStart, curCol + _reduced.getDistToNextNewline());
      int pos = text.indexOf("//");
      boolean goodWing = true;
      for (int i = pos - 1; (i >= 0) && goodWing; i--) {
        char c = text.charAt(i);
        if (!(((c == ' ') || (c == ' ')) || (c == ' '))) {
          goodWing = false;
        }
      }
      if ((pos >= 0) && goodWing) {
        remove(lineStart + pos, 2);
        _indentLine();
      }
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
  }
}

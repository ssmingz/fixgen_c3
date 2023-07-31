class PlaceHold {
  void updateSelection(int startOffset, int replacedLength, int newLength) {
    if (selection.y <= startOffset) {
      if (wordWrap || visualWrap) {
        setCaretLocation();
      }
      return;
    }
    if (selection.x < startOffset) {
      internalRedrawRange(selection.x, startOffset - selection.x);
    }
    if ((selection.y > (startOffset + replacedLength))
        && (selection.x < (startOffset + replacedLength))) {
      int netNewLength = newLength - replacedLength;
      int redrawStart = startOffset + newLength;
      internalRedrawRange(redrawStart, (selection.y + netNewLength) - redrawStart);
    }
    if ((selection.y > startOffset) && (selection.x < (startOffset + replacedLength))) {
      setSelection(startOffset + newLength, 0, true, false);
    } else {
      setSelection(
          (selection.x + newLength) - replacedLength, selection.y - selection.x, true, false);
    }
    setCaretLocation();
  }
}

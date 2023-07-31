class PlaceHold {
  int getBottomIndex() {
    return Math.max(0, Math.min(content.getLineCount(), topIndex + getLineCountWhole()) - 1);
  }
}

class PlaceHold {
  int getBottomIndex() {
    return Math.min(content.getLineCount(), topIndex + getLineCountWhole()) - 1;
  }
}

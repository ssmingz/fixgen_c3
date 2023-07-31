class PlaceHold {
  int getPartialBottomIndex() {
    int partialLineCount = ((int) (Math.ceil(((float) (getClientArea().height)) / lineHeight)));
    return Math.min(content.getLineCount(), topIndex + partialLineCount) - 1;
  }
}

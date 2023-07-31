class PlaceHold {
  void calculateContentWidth() {
    if (lineHeight != 0) {
      int itemCount = ((int) (Math.ceil(((float) (getClientArea().height)) / lineHeight)));
      calculateContentWidth(topIndex, Math.min(itemCount, content.getLineCount() - topIndex));
    }
  }
}

class PlaceHold {
  int getLineCountTruncated() {
    int lineCount;
    if (lineHeight != 0) {
      lineCount = ((int) (Compatibility.ceil(((float) (getClientArea().height)) / lineHeight)));
    } else {
      lineCount = 1;
    }
    return lineCount;
  }
}

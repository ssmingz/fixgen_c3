class ContentWidthCache {
  public ContentWidthCache(StyledText parent, int lineCount) {
    this.lineCount = lineCount;
    this.parent = parent;
    lineWidth = new int[lineCount];
    reset(0, lineCount);
  }
}

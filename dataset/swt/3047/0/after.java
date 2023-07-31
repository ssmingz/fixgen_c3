class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    if (currentOffset != (-1)) {
      StyledText text = ((StyledText) (getControl()));
      drawCaret(text, currentOffset, -1);
    }
    scrollBeginTime = 0;
    scrollX = -1;
    scrollY = -1;
  }
}

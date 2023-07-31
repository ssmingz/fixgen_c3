class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    if (!checkWidget(event)) {
      return;
    }
    if (currentOffset != (-1)) {
      DropTarget dt = ((DropTarget) (event.widget));
      StyledText text = ((StyledText) (dt.getControl()));
      drawCaret(text, currentOffset, -1);
    }
    scrollBeginTime = 0;
    scrollX = -1;
    scrollY = -1;
  }
}

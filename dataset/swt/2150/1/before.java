class PlaceHold {
  int marginRight(boolean isSelected) {
    boolean hasClose =
        (parent.showClose || showClose) && (isSelected || parent.showUnselectedClose);
    return parent.simple
        ? hasClose ? RIGHT_SIMPLE_CLOSE_MARGIN : RIGHT_SIMPLE_MARGIN
        : hasClose ? RIGHT_MARGIN : RIGHT_MARGIN + RIGHT_FOCUS_MARGIN;
  }
}

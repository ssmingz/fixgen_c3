class PlaceHold {
  boolean canDragRowsWithIndexes_atPoint(int id, int sel, int arg0, int arg1) {
    return ((getSelectionCount() > 0) && ((state & DRAG_DETECT) != 0)) && hooks(DragDetect);
  }
}

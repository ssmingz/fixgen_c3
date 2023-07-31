class PlaceHold {
  boolean applyRule(AbstractDJDocument doc, Indenter.IndentReason reason) {
    int cursor;
    cursor = doc.getLineStartPos(doc.getCurrentLocation());
    if (cursor == 0) {
      return false;
    }
    cursor = cursor - 1;
    cursor = doc.getLineStartPos(cursor);
    doc.resetReducedModelLocation();
    ReducedModelState state = doc.stateAtRelLocation(cursor - doc.getCurrentLocation());
    return !state.equals(INSIDE_BLOCK_COMMENT);
  }
}

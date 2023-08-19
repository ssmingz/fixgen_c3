class PlaceHold {
  protected void setLeftDirty(boolean dirty) {
    if (isLeftDirty() != dirty) {
      fLeftSaveAction.setEnabled(dirty);
      fireDirtyState(dirty);
    }
  }
}

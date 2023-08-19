class PlaceHold {
  protected void setLeftDirty(boolean dirty) {
    if (isLeftDirty() != dirty) {
      fLeftSaveAction.setEnabled(dirty);
      // Only fire the event if the combined dirty state has changed
      if (((!isRightDirty()) && (!isLeftDirty())) || ((!isRightDirty()) && isLeftDirty()))
        fireDirtyState(dirty);
    }
  }
}

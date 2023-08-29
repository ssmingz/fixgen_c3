class PlaceHold {
  protected void setLeftDirty(boolean dirty) {
    if (isLeftDirty() != dirty) {
      fLeftSaveAction.setEnabled(dirty);
      if (((!isRightDirty()) && (!isLeftDirty())) || ((!isRightDirty()) && isLeftDirty()))
        fireDirtyState(dirty);
    }
  }
}

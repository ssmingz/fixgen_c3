class PlaceHold {
  protected void setRightDirty(boolean dirty) {
    if (isRightDirty() != dirty) {
      fRightSaveAction.setEnabled(dirty);
      fireDirtyState(dirty);
    }
  }
}

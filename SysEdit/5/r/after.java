class PlaceHold {
  protected void setRightDirty(boolean dirty) {
    if (isRightDirty() != dirty) {
      fRightSaveAction.setEnabled(dirty);
      // Only fire the event if the combined dirty state has changed
      if (((!isRightDirty()) && (!isLeftDirty())) || (isRightDirty() && (!isLeftDirty())))
        fireDirtyState(dirty);
    }
  }
}

class PlaceHold {
  protected void setRightDirty(boolean dirty) {
    if (isRightDirty() != dirty) {
      fRightSaveAction.setEnabled(dirty);
      if (((!isRightDirty()) && (!isLeftDirty())) || (isRightDirty() && (!isLeftDirty())))
        fireDirtyState(dirty);
    }
  }
}

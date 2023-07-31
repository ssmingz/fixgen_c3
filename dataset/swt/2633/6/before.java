class PlaceHold {
  public int GetPersistentDescriptor(int aPersistentDescriptor) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 12, getAddress(), aPersistentDescriptor);
  }
}

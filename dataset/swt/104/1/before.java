class PlaceHold {
  public int GetPersistentDescriptor(long aPersistentDescriptor) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 12, getAddress(), aPersistentDescriptor);
  }
}

class PlaceHold {
  public int SetPersistentDescriptor(long aPersistentDescriptor) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 13, getAddress(), aPersistentDescriptor);
  }
}

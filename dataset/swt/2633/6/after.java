class PlaceHold {
  public int GetPersistentDescriptor(int aPersistentDescriptor) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 12, getAddress(), aPersistentDescriptor);
  }
}

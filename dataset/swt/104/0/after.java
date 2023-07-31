class PlaceHold {
  public int SetPersistentDescriptor(long aPersistentDescriptor) {
    if (IsXULRunner17) {
      return super.SetPersistentDescriptor(aPersistentDescriptor);
    }
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 13, getAddress(), aPersistentDescriptor);
  }
}

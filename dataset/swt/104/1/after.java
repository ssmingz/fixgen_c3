class PlaceHold {
  public int GetPersistentDescriptor(long aPersistentDescriptor) {
    if (IsXULRunner17) {
      return super.GetPersistentDescriptor(aPersistentDescriptor);
    }
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 12, getAddress(), aPersistentDescriptor);
  }
}

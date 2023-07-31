class PlaceHold {
  public int GetSubjectName(int aSubjectName) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 23 : 22), getAddress(), aSubjectName);
  }
}

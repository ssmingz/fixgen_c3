class PlaceHold {
  public int SetIntPref(byte[] aPrefName, int aValue) {
    return XPCOM.VtblCall(this.getMethodIndex("setIntPref"), getAddress(), aPrefName, aValue);
  }
}

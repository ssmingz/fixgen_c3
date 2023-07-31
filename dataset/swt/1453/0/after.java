class PlaceHold {
  public boolean isSupportedType(TransferData transferData) {
    if (transferData == null) {
      return false;
    }
    int[] types = getTypeIds();
    for (int i = 0; i < types.length; i++) {
      if (transferData.type == types[i]) {
        return true;
      }
    }
    return false;
  }
}

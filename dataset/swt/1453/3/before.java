class PlaceHold {
  public boolean isSupportedType(TransferData transferData) {
    int[] types = getTypeIds();
    for (int i = 0; i < types.length; i++) {
      if (transferData.type == types[i]) {
        return true;
      }
    }
    return false;
  }
}

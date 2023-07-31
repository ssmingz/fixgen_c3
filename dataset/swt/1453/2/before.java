class PlaceHold {
  public boolean isSupportedType(TransferData transferData) {
    int[] types = getTypeIds();
    for (int i = 0; i < types.length; i++) {
      FORMATETC format = transferData.formatetc;
      if (((format.cfFormat == types[i])
              && ((format.dwAspect & COM.DVASPECT_CONTENT) == COM.DVASPECT_CONTENT))
          && ((format.tymed & COM.TYMED_HGLOBAL) == COM.TYMED_HGLOBAL)) {
        return true;
      }
    }
    return false;
  }
}

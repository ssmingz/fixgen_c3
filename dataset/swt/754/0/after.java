class PlaceHold {
  int Skip(int celt) {
    if (DEBUG) {
      print(this + ".IEnumVARIANT::Skip");
    }
    if ((iaccessible != null) && (accessibleControlListeners.size() == 0)) {
      long[] ppvObject = new long[1];
      int code = iaccessible.QueryInterface(IIDIEnumVARIANT, ppvObject);
      if (code != COM.S_OK) {
        return code;
      }
      IEnumVARIANT ienumvariant = new IEnumVARIANT(ppvObject[0]);
      code = ienumvariant.Skip(celt);
      ienumvariant.Release();
      return code;
    }
    if (celt < 1) {
      return COM.E_INVALIDARG;
    }
    enumIndex += celt;
    if (enumIndex > (variants.length - 1)) {
      enumIndex = variants.length - 1;
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}

class PlaceHold {
  protected final ClassInfo findClassAlias(final String aName) {
    ClassInfo ci = null;
    for (int i = mTypeParams.size() - 1; i >= 0; i--) {
      final Map<String, ClassInfo> paramMap = mTypeParams.get(i);
      ci = paramMap.get(aName);
      if (ci != null) {
        break;
      }
    }
    return ci;
  }
}

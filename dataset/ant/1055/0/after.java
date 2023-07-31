class PlaceHold {
  private String getDefaultReferenceParameter() {
    if (m_includeDefaultReferences) {
      StringBuffer s = new StringBuffer("/reference:");
      s.append(DEFAULT_REFERENCE_LIST);
      return new String(s);
    } else {
      return null;
    }
  }
}

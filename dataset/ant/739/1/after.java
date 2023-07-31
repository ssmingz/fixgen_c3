class PlaceHold {
  protected void maybeSetResultPropertyValue(int result) throws TaskException {
    final String res = Integer.toString(result);
    if (m_resultProperty != null) {
      final String name = m_resultProperty;
      getContext().setProperty(name, res);
    }
  }
}

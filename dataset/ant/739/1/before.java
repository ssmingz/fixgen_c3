class PlaceHold {
  protected void maybeSetResultPropertyValue(int result) throws TaskException {
    String res = Integer.toString(result);
    if (m_resultProperty != null) {
      setProperty(m_resultProperty, res);
    }
  }
}

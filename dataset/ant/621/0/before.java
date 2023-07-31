class PlaceHold {
  public void setValue(final Object value) throws TaskException {
    if (null != m_value) {
      throw new TaskException("Value can not be set multiple times");
    }
    m_value = value;
  }
}

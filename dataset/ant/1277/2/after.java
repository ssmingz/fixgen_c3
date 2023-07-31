class PlaceHold {
  public void setValue(final Object value) throws TaskException {
    if (null != m_value) {
      final String message = REZ.getString("property.multi-set.error");
      throw new TaskException(message);
    }
    m_value = value;
  }
}

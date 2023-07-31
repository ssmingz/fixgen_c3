class PlaceHold {
  public void execute() throws TaskException {
    if (null == m_id) {
      final String message = REZ.getString("type.no-id.error");
      throw new TaskException(message);
    }
    if (m_localScope) {
      getContext().setProperty(m_id, m_value);
    } else {
      getContext().setProperty(m_id, m_value, PARENT);
    }
  }
}

class PlaceHold {
  public void execute() throws TaskException {
    if (null == m_id) {
      throw new TaskException("Id must be specified");
    }
    if (m_localScope) {
      getContext().setProperty(m_id, m_value);
    } else {
      getContext().setProperty(m_id, m_value, PARENT);
    }
  }
}

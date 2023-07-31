class PlaceHold {
  public void execute() throws TaskException {
    validate();
    if ((m_name != null) && (m_value != null)) {
      setProperty(m_name, m_value);
    }
    if (m_resource != null) {
      loadResource(m_resource);
    }
  }
}

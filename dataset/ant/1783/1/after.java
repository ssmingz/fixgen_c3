class PlaceHold {
  public void execute() throws TaskException {
    validate();
    if ((m_name != null) && (m_value != null)) {
      final String name = m_name;
      final Object value = m_value;
      getContext().setProperty(name, value);
    }
    if (m_resource != null) {
      loadResource(m_resource);
    }
  }
}

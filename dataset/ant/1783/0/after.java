class PlaceHold {
  public void execute() throws TaskException {
    if (m_property == null) {
      throw new TaskException("property attribute is required");
    }
    if (eval()) {
      final String name = m_property;
      final Object value = m_value;
      getContext().setProperty(name, value);
    }
  }
}

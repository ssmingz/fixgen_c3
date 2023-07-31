class PlaceHold {
  private void setupComponents() throws Exception {
    for (Iterator iterator = m_components.iterator(); iterator.hasNext(); ) {
      final Object component = iterator.next();
      setupObject(component, m_parameters);
    }
  }
}

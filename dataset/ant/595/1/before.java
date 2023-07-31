class PlaceHold {
  private void setupComponents() throws Exception {
    for (Iterator iterator = m_components.iterator(); iterator.hasNext(); ) {
      final Component component = ((Component) (iterator.next()));
      setupObject(component, m_parameters);
    }
  }
}

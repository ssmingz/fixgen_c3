class PlaceHold {
  private DefaultComponentManager createComponentManager() {
    final DefaultComponentManager componentManager = new DefaultComponentManager();
    componentManager.put(ROLE, m_converter);
    componentManager.put(ROLE, m_projectManager);
    componentManager.put(ROLE, m_builder);
    componentManager.put(ROLE, m_roleManager);
    componentManager.put(ROLE, m_deployer);
    componentManager.put(ROLE, m_typeManager);
    componentManager.put(ROLE, m_converterRegistry);
    componentManager.put(ROLE, m_configurer);
    componentManager.put(ROLE, m_executor);
    return componentManager;
  }
}

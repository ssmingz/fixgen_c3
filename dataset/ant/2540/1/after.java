class PlaceHold {
  public void initialize() throws Exception {
    setupComponents();
    m_deployer = ((Deployer) (m_serviceManager.lookup(ROLE)));
    m_typeManager = ((TypeManager) (m_serviceManager.lookup(ROLE)));
    final ServiceManager projServiceManager =
        ((ServiceManager)
            (createService(ServiceManager.class, PREFIX + "service.InstantiatingServiceManager")));
    setupObject(projServiceManager, m_serviceManager, null);
    m_components.add(projServiceManager);
    m_workspaceServiceManager = new MultiSourceServiceManager();
    m_workspaceServiceManager.add(projServiceManager);
    m_workspaceServiceManager.add(m_serviceManager);
  }
}

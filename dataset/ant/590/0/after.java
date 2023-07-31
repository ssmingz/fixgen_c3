class PlaceHold {
  public void service(final ServiceManager manager) throws ServiceException {
    m_serviceManager = manager;
    m_roleManager = ((RoleManager) (manager.lookup(ROLE)));
    m_typeManager = ((TypeManager) (manager.lookup(ROLE)));
  }
}

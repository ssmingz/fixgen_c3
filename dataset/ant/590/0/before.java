class PlaceHold {
  public void service(final ServiceManager manager) throws ServiceException {
    m_serviceManager = manager;
    m_roleManager = ((RoleManager) (manager.lookup(ROLE)));
    final TypeManager typeManager = ((TypeManager) (manager.lookup(ROLE)));
    try {
      m_typeFactory = typeManager.getFactory(ServiceFactory.class);
    } catch (final TypeException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }
}

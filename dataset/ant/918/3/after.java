class PlaceHold {
  public void service(final ServiceManager serviceManager) throws ServiceException {
    m_converter = ((Converter) (serviceManager.lookup(ROLE)));
    m_typeManager = ((TypeManager) (serviceManager.lookup(ROLE)));
    m_roleManager = ((RoleManager) (serviceManager.lookup(ROLE)));
  }
}

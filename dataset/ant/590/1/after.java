class PlaceHold {
  public void service(final ServiceManager serviceManager) throws ServiceException {
    m_typeManager = ((TypeManager) (serviceManager.lookup(ROLE)));
  }
}

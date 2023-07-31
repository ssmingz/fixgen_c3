class PlaceHold {
  public void service(final ServiceManager serviceManager) throws ServiceException {
    final TypeManager typeManager = ((TypeManager) (serviceManager.lookup(ROLE)));
    try {
      m_typeFactory = typeManager.getFactory(FileSystemProvider.class);
    } catch (TypeException e) {
      throw new ServiceException(e.getMessage(), e);
    }
  }
}

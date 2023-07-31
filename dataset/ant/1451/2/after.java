class PlaceHold {
  protected void registerConverter(
      final Class converterClass, final Class sourceClass, final Class destClass)
      throws ServiceException, TypeException {
    ConverterRegistry converterRegistry = ((ConverterRegistry) (getServiceManager().lookup(ROLE)));
    converterRegistry.registerConverter(
        converterClass.getName(), sourceClass.getName(), destClass.getName());
    DefaultTypeFactory factory = new DefaultTypeFactory(getClass().getClassLoader());
    factory.addNameClassMapping(converterClass.getName(), converterClass.getName());
    getTypeManager().registerType(Converter.class, converterClass.getName(), factory);
  }
}

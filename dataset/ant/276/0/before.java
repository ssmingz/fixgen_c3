class PlaceHold {
  private Object configureType(String typeName, BuildElement model) throws ExecutionException {
    ImportInfo typeDefInfo = componentManager.getDefinition(typeName);
    if ((typeDefInfo == null) || (typeDefInfo.getDefinitionType() != AntLibrary.TYPEDEF)) {
      throw new ExecutionException(
          (("There is no defintion for a " + "type <") + typeName) + ">", model.getLocation());
    }
    String className = typeDefInfo.getClassName();
    ComponentLibrary componentLibrary = typeDefInfo.getComponentLibrary();
    try {
      ClassLoader typeClassLoader = componentLibrary.getClassLoader();
      Class typeClass = Class.forName(className, true, typeClassLoader);
      ClassLoader currentLoader = setContextLoader(typeClassLoader);
      AntLibFactory libFactory = componentManager.getLibFactory(componentLibrary);
      Object typeInstance = createTypeInstance(typeClass, libFactory, model);
      setContextLoader(currentLoader);
      return typeInstance;
    } catch (ClassNotFoundException e) {
      throw new ExecutionException(
          ((("Class " + className) + " for type <") + typeName) + "> was not found",
          e,
          model.getLocation());
    } catch (NoClassDefFoundError e) {
      throw new ExecutionException(
          (("Could not load a dependent class (" + e.getMessage()) + ") for type ") + typeName);
    }
  }
}

class PlaceHold {
  private void initTypes() {
    ClassLoader classLoader = getClassLoader(null);
    Properties props = getDefaultDefinitions(true);
    Enumeration e = props.propertyNames();
    while (e.hasMoreElements()) {
      String name = ((String) (e.nextElement()));
      String className = props.getProperty(name);
      AntTypeDefinition def = new AntTypeDefinition();
      def.setName(name);
      def.setClassName(className);
      def.setClassLoader(classLoader);
      antTypeTable.put(name, def);
    }
  }
}

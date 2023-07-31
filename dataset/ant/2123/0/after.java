class PlaceHold {
  private void initTasks() {
    ClassLoader classLoader = getClassLoader(null);
    Properties props = getDefaultDefinitions(false);
    Enumeration e = props.propertyNames();
    while (e.hasMoreElements()) {
      String name = ((String) (e.nextElement()));
      String className = props.getProperty(name);
      AntTypeDefinition def = new AntTypeDefinition();
      def.setName(name);
      def.setClassName(className);
      def.setClassLoader(classLoader);
      def.setAdaptToClass(Task.class);
      def.setAdapterClass(TaskAdapter.class);
      antTypeTable.put(name, def);
    }
  }
}

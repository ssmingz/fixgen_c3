class PlaceHold {
  private void initTasks() {
    ClassLoader classLoader = null;
    classLoader = getClassLoader(classLoader);
    String dataDefs = MagicNames.TASKDEF_PROPERTIES_RESOURCE;
    InputStream in = null;
    try {
      Properties props = new Properties();
      in = this.getClass().getResourceAsStream(dataDefs);
      if (in == null) {
        throw new BuildException(ERROR_NO_TASK_LIST_LOAD);
      }
      props.load(in);
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
    } catch (IOException ex) {
      throw new BuildException(ERROR_NO_TASK_LIST_LOAD);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (Exception ignore) {
        }
      }
    }
  }
}

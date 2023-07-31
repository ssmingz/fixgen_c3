class PlaceHold {
  public void execute() throws BuildException {
    if ((name == null) || (value == null)) {
      String msg = "name or classname attributes of taskdef element " + "are undefined";
      throw new BuildException(msg);
    }
    try {
      ClassLoader loader = null;
      if (classpath != null) {
        AntClassLoader al = new AntClassLoader(project, classpath, false);
        al.addSystemPackageRoot("org.apache.tools.ant");
        loader = al;
      } else {
        loader = this.getClass().getClassLoader();
      }
      Class taskClass = null;
      if (loader != null) {
        taskClass = loader.loadClass(value);
      } else {
        taskClass = Class.forName(value);
      }
      project.addTaskDefinition(name, taskClass);
    } catch (ClassNotFoundException cnfe) {
      String msg = ("taskdef class " + value) + " cannot be found";
      throw new BuildException(msg, cnfe, location);
    } catch (NoClassDefFoundError ncdfe) {
      String msg = ("taskdef class " + value) + " cannot be found";
      throw new BuildException(msg, ncdfe, location);
    }
  }
}

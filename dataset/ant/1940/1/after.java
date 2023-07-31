class PlaceHold {
  public void selectorCreate() {
    if ((classname != null) && (classname.length() > 0)) {
      try {
        Class c = null;
        if (classpath == null) {
          c = Class.forName(classname);
        } else {
          AntClassLoader al = getProject().createClassLoader(classpath);
          c = al.loadClass(classname);
          AntClassLoader.initializeClass(c);
        }
        dynselector = ((FileSelector) (c.newInstance()));
        final Project project = getProject();
        if (project != null) {
          project.setProjectReference(dynselector);
        }
      } catch (ClassNotFoundException cnfexcept) {
        setError(("Selector " + classname) + " not initialized, no such class");
      } catch (InstantiationException iexcept) {
        setError(("Selector " + classname) + " not initialized, could not create class");
      } catch (IllegalAccessException iaexcept) {
        setError(("Selector " + classname) + " not initialized, class not accessible");
      }
    } else {
      setError("There is no classname specified");
    }
  }
}

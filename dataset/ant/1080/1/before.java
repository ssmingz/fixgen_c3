class PlaceHold {
  private boolean checkClass(String classname) {
    try {
      Class requiredClass = null;
      if (ignoreSystemclasses) {
        loader = getProject().createClassLoader(classpath);
        loader.setParentFirst(false);
        loader.addJavaLibraries();
        if (loader != null) {
          try {
            requiredClass = loader.findClass(classname);
          } catch (SecurityException se) {
            return true;
          }
        } else {
          return false;
        }
      } else if (loader != null) {
        requiredClass = loader.loadClass(classname);
      } else {
        ClassLoader l = this.getClass().getClassLoader();
        if (l != null) {
          requiredClass = l.loadClass(classname);
        } else {
          requiredClass = Class.forName(classname);
        }
      }
      AntClassLoader.initializeClass(requiredClass);
      return true;
    } catch (ClassNotFoundException e) {
      log(("class \"" + classname) + "\" was not found", MSG_DEBUG);
      return false;
    } catch (NoClassDefFoundError e) {
      log(
          ((("Could not load dependent class \"" + e.getMessage()) + "\" for class \"") + classname)
              + "\"",
          MSG_DEBUG);
      return false;
    }
  }
}

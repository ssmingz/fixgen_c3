class PlaceHold {
  protected void addDefinition(ClassLoader al, String name, String classname)
      throws BuildException {
    Class cl = null;
    try {
      try {
        if (onError != OnError.IGNORE) {
          cl = Class.forName(classname, true, al);
        }
        if (adapter != null) {
          adapterClass = Class.forName(adapter, true, al);
        }
        if (adaptTo != null) {
          adaptToClass = Class.forName(adaptTo, true, al);
        }
        AntTypeDefinition def = new AntTypeDefinition();
        def.setName(name);
        def.setClassName(classname);
        def.setClass(cl);
        def.setAdapterClass(adapterClass);
        def.setAdaptToClass(adaptToClass);
        def.setClassLoader(al);
        if (cl != null) {
          def.checkClass(getProject());
        }
        ComponentHelper.getComponentHelper(getProject()).addDataTypeDefinition(def);
      } catch (ClassNotFoundException cnfe) {
        String msg = ((getTaskName() + " class ") + classname) + " cannot be found";
        throw new BuildException(msg, cnfe, getLocation());
      } catch (NoClassDefFoundError ncdfe) {
        String msg =
            (((getTaskName() + " A class needed by class ") + classname) + " cannot be found: ")
                + ncdfe.getMessage();
        throw new BuildException(msg, ncdfe, location);
      }
    } catch (BuildException ex) {
      switch (onError) {
        case OnError.FAIL:
          throw ex;
        case OnError.REPORT:
          log((ex.getLocation() + "Warning: ") + ex.getMessage(), MSG_WARN);
          break;
        default:
          log(ex.getLocation() + ex.getMessage(), MSG_DEBUG);
      }
    }
  }
}

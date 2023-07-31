class PlaceHold {
  public void execute() throws BuildException {
    Method setProjectM = null;
    try {
      Class c = proxy.getClass();
      setProjectM = c.getMethod("setProject", new Class[] {Project.class});
      if (setProjectM != null) {
        setProjectM.invoke(proxy, new Object[] {project});
      }
    } catch (NoSuchMethodException e) {
    } catch (Exception ex) {
      log("Error setting project in " + proxy.getClass(), MSG_ERR);
      throw new BuildException(ex);
    }
    Method executeM = null;
    try {
      Class c = proxy.getClass();
      executeM = c.getMethod("execute", new Class[0]);
      if (executeM == null) {
        log("No public execute() in " + proxy.getClass(), MSG_ERR);
        throw new BuildException("No public execute() in " + proxy.getClass());
      }
      executeM.invoke(proxy, null);
      return;
    } catch (InvocationTargetException ie) {
      log("Error in " + proxy.getClass(), MSG_ERR);
      Throwable t = ie.getTargetException();
      if (t instanceof BuildException) {
        throw ((BuildException) (t));
      } else {
        throw new BuildException(t);
      }
    } catch (Exception ex) {
      log("Error in " + proxy.getClass(), MSG_ERR);
      throw new BuildException(ex);
    }
  }
}

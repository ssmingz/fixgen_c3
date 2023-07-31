class PlaceHold {
  public void execute() throws TaskException {
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
      throw new TaskException("Error", ex);
    }
    Method executeM = null;
    try {
      Class c = proxy.getClass();
      executeM = c.getMethod("execute", new Class[0]);
      if (executeM == null) {
        log("No public execute() in " + proxy.getClass(), MSG_ERR);
        throw new TaskException("No public execute() in " + proxy.getClass());
      }
      executeM.invoke(proxy, null);
      return;
    } catch (Exception ex) {
      log("Error in " + proxy.getClass(), MSG_ERR);
      throw new TaskException("Error", ex);
    }
  }
}

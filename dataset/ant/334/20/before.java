class PlaceHold {
  public void execute() throws BuildException {
    try {
      addBeans(project.getProperties());
      addBeans(project.getUserProperties());
      addBeans(project.getTargets());
      addBeans(project.getReferences());
      beans.put("project", getProject());
      beans.put("self", this);
      BSFManager manager = new BSFManager();
      for (Enumeration e = beans.keys(); e.hasMoreElements(); ) {
        String key = ((String) (e.nextElement()));
        Object value = beans.get(key);
        manager.declareBean(key, value, value.getClass());
      }
      manager.exec(language, "<ANT>", 0, 0, script);
    } catch (BSFException be) {
      Throwable t = be;
      Throwable te = be.getTargetException();
      if (te != null) {
        if (te instanceof BuildException) {
          throw ((BuildException) (te));
        } else {
          t = te;
        }
      }
      throw new BuildException(t);
    }
  }
}

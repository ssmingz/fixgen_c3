class PlaceHold {
  private void copyReference(String oldKey, String newKey) {
    Object orig = project.getReference(oldKey);
    Class c = orig.getClass();
    Object copy = orig;
    try {
      Method cloneM = c.getMethod("clone", new Class[0]);
      if (cloneM != null) {
        copy = cloneM.invoke(orig, new Object[0]);
      }
    } catch (Exception e) {
    }
    if (copy instanceof ProjectComponent) {
      ((ProjectComponent) (copy)).setProject(newProject);
    } else {
      try {
        Method setProjectM = c.getMethod("setProject", new Class[] {Project.class});
        if (setProjectM != null) {
          setProjectM.invoke(copy, new Object[] {newProject});
        }
      } catch (NoSuchMethodException e) {
      } catch (Exception e2) {
        String msg = ("Error setting new project instance for " + "reference with id ") + oldKey;
        throw new BuildException(msg, e2, location);
      }
    }
    newProject.addReference(newKey, copy);
  }
}

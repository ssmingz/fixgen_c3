class PlaceHold {
  public void setFileLastModified(File file, long time) throws BuildException {
    if (Project.getJavaVersion() == Project.JAVA_1_1) {
      return;
    }
    Long[] times = new Long[1];
    if (time < 0) {
      times[0] = new Long(System.currentTimeMillis());
    } else {
      times[0] = new Long(time);
    }
    try {
      getSetLastModified().invoke(file, times);
    } catch (InvocationTargetException ite) {
      Throwable nested = ite.getTargetException();
      throw new BuildException(("Exception setting the modification time " + "of ") + file, nested);
    } catch (Throwable other) {
      throw new BuildException(("Exception setting the modification time " + "of ") + file, other);
    }
  }
}

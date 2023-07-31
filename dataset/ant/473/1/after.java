class PlaceHold {
  public void addText(Object element, String text) {
    if (addText == null) {
      String msg =
          ("Class " + element.getClass().getName()) + " doesn't support nested text elements";
      throw new BuildException(msg);
    }
    try {
      addText.invoke(element, new String[] {text});
    } catch (IllegalAccessException ie) {
      throw new BuildException(ie);
    } catch (InvocationTargetException ite) {
      Throwable t = ite.getTargetException();
      if (t instanceof BuildException) {
        throw ((BuildException) (t));
      }
      throw new BuildException(t);
    }
  }
}

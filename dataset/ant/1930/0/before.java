class PlaceHold {
  private void addInputHandler(Project project) throws BuildException {
    InputHandler handler = null;
    if (inputHandlerClassname == null) {
      handler = new DefaultInputHandler();
    } else {
      try {
        handler = ((InputHandler) (Class.forName(inputHandlerClassname).newInstance()));
      } catch (ClassCastException e) {
        String msg =
            ("The specified input handler class " + inputHandlerClassname)
                + " does not implement the InputHandler interface";
        throw new BuildException(msg);
      } catch (Exception e) {
        String msg =
            ((("Unable to instantiate specified input handler " + "class ") + inputHandlerClassname)
                    + " : ")
                + e.getClass().getName();
        throw new BuildException(msg);
      }
    }
    project.setInputHandler(handler);
  }
}

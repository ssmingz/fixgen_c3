class PlaceHold {
  private void _setupDebugPanel(ConfigPanel panel) {
    if (_mainFrame.getModel().getDebugManager() == null) {
      String howto =
          ((((("\n"
                   + "The debugger is not currently active.  To use the debugger, you must\n"
                   + "include Sun\'s tools.jar or jpda.jar on your classpath when starting"
                   + " DrJava.\n")
                              + "Do not use the \"-jar\" option, because it overrides the"
                              + " classpath.\n")
                          + "For example, in Windows you might type:\n\n")
                      + "  java -classpath drjava.jar;c:\\path\\tools.jar"
                      + " edu.rice.cs.drjava.DrJava\n\n")
                  + "(Substituting the correct path for tools.jar.)\n")
              + "See the user documentation for more details.\n";
      panel.addComponent(new LabelComponent(howto, this));
    }
    VectorOptionComponent sourcePath =
        new VectorOptionComponent(OptionConstants.DEBUG_SOURCEPATH, "Sourcepath", this);
    sourcePath.setFileFilter(
        new FileFilter() {
          public boolean accept(File f) {
            if (f.isDirectory()) {
              return true;
            }
            return false;
          }

          public String getDescription() {
            return "Source Directories";
          }
        });
    panel.addComponent(sourcePath);
    panel.addComponent(
        new BooleanOptionComponent(
            OptionConstants.DEBUG_SHOW_THREADS, "Show Current Threads Tab", this));
    panel.addComponent(
        new BooleanOptionComponent(
            OptionConstants.DEBUG_STEP_JAVA, "Step Into Java Classes", this));
    panel.addComponent(
        new BooleanOptionComponent(
            OptionConstants.DEBUG_STEP_INTERPRETER, "Step Into Interpreter Classes", this));
    panel.addComponent(
        new BooleanOptionComponent(
            OptionConstants.DEBUG_STEP_DRJAVA, "Step Into DrJava Classes", this));
    panel.displayComponents();
  }
}

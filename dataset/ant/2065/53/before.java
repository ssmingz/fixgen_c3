class PlaceHold {
  protected BuildException getNotFoundException(String what, String elementName) {
    String lSep = System.getProperty("line.separator");
    String msg =
        ((((((((((((((((((((((((("Could not create " + what) + " of type: ") + elementName) + ".")
                                                                                                + lSep)
                                                                                            + "Ant could"
                                                                                            + " not find"
                                                                                            + " the task"
                                                                                            + " or a"
                                                                                            + " class"
                                                                                            + " this")
                                                                                        + lSep)
                                                                                    + "task relies"
                                                                                    + " upon.")
                                                                                + lSep)
                                                                            + "Common solutions are"
                                                                            + " to use taskdef to"
                                                                            + " declare")
                                                                        + lSep)
                                                                    + "your task, or, if this is an"
                                                                    + " optional task,")
                                                                + lSep)
                                                            + "to put the optional.jar and all"
                                                            + " required libraries of")
                                                        + lSep)
                                                    + "this task in the lib directory of")
                                                + lSep)
                                            + "your ant installation (ANT_HOME).")
                                        + lSep)
                                    + "There is also the possibility that your build file ")
                                + lSep)
                            + "is written to work with a more recent version of ant ")
                        + lSep)
                    + "than the one you are using, in which case you have to ")
                + lSep)
            + "upgrade.";
    return new BuildException(msg, location);
  }
}

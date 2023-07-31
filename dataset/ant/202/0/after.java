class PlaceHold {
  protected BuildException getNotFoundException(String what, String elementName) {
    String lSep = System.getProperty("line.separator");
    String msg =
        (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("Could not create " + what)
                                                                                                                                                                                                                                                                + " of type:"
                                                                                                                                                                                                                                                                + " ")
                                                                                                                                                                                                                                                            + elementName)
                                                                                                                                                                                                                                                        + ".")
                                                                                                                                                                                                                                                    + lSep)
                                                                                                                                                                                                                                                + lSep)
                                                                                                                                                                                                                                            + "Ant could"
                                                                                                                                                                                                                                            + " not find"
                                                                                                                                                                                                                                            + " the task"
                                                                                                                                                                                                                                            + " or a"
                                                                                                                                                                                                                                            + " class"
                                                                                                                                                                                                                                            + " this"
                                                                                                                                                                                                                                            + " ")
                                                                                                                                                                                                                                        + "task relies"
                                                                                                                                                                                                                                        + " upon.")
                                                                                                                                                                                                                                    + lSep)
                                                                                                                                                                                                                                + lSep)
                                                                                                                                                                                                                            + "This is"
                                                                                                                                                                                                                            + " common"
                                                                                                                                                                                                                            + " and has"
                                                                                                                                                                                                                            + " a number"
                                                                                                                                                                                                                            + " of causes;"
                                                                                                                                                                                                                            + " the usual"
                                                                                                                                                                                                                            + " ")
                                                                                                                                                                                                                        + lSep)
                                                                                                                                                                                                                    + "solutions"
                                                                                                                                                                                                                    + " are to"
                                                                                                                                                                                                                    + " read"
                                                                                                                                                                                                                    + " the manual"
                                                                                                                                                                                                                    + " pages"
                                                                                                                                                                                                                    + " then"
                                                                                                                                                                                                                    + " download"
                                                                                                                                                                                                                    + " and")
                                                                                                                                                                                                                + lSep)
                                                                                                                                                                                                            + "install"
                                                                                                                                                                                                            + " needed"
                                                                                                                                                                                                            + " JAR files,"
                                                                                                                                                                                                            + " or fix"
                                                                                                                                                                                                            + " the build"
                                                                                                                                                                                                            + " file:"
                                                                                                                                                                                                            + " ")
                                                                                                                                                                                                        + lSep)
                                                                                                                                                                                                    + " - You"
                                                                                                                                                                                                    + " have"
                                                                                                                                                                                                    + " misspelt"
                                                                                                                                                                                                    + " '")
                                                                                                                                                                                                + elementName)
                                                                                                                                                                                            + "'.")
                                                                                                                                                                                        + lSep)
                                                                                                                                                                                    + "   Fix:"
                                                                                                                                                                                    + " check"
                                                                                                                                                                                    + " your"
                                                                                                                                                                                    + " spelling.")
                                                                                                                                                                                + lSep)
                                                                                                                                                                            + " - The"
                                                                                                                                                                            + " task"
                                                                                                                                                                            + " needs"
                                                                                                                                                                            + " an external"
                                                                                                                                                                            + " JAR file"
                                                                                                                                                                            + " to execute")
                                                                                                                                                                        + lSep)
                                                                                                                                                                    + "   and"
                                                                                                                                                                    + " this"
                                                                                                                                                                    + " is not"
                                                                                                                                                                    + " found"
                                                                                                                                                                    + " at the"
                                                                                                                                                                    + " right"
                                                                                                                                                                    + " place"
                                                                                                                                                                    + " in the"
                                                                                                                                                                    + " classpath.")
                                                                                                                                                                + lSep)
                                                                                                                                                            + "   Fix:"
                                                                                                                                                            + " check"
                                                                                                                                                            + " the documentation"
                                                                                                                                                            + " for dependencies.")
                                                                                                                                                        + lSep)
                                                                                                                                                    + "   Fix:"
                                                                                                                                                    + " declare"
                                                                                                                                                    + " the task.")
                                                                                                                                                + lSep)
                                                                                                                                            + " - The"
                                                                                                                                            + " task"
                                                                                                                                            + " is an"
                                                                                                                                            + " Ant optional"
                                                                                                                                            + " task"
                                                                                                                                            + " and optional.jar"
                                                                                                                                            + " is absent")
                                                                                                                                        + lSep)
                                                                                                                                    + "   Fix:"
                                                                                                                                    + " look"
                                                                                                                                    + " for optional.jar"
                                                                                                                                    + " in ANT_HOME/lib,"
                                                                                                                                    + " download"
                                                                                                                                    + " if needed")
                                                                                                                                + lSep)
                                                                                                                            + " - The"
                                                                                                                            + " task"
                                                                                                                            + " was not"
                                                                                                                            + " built"
                                                                                                                            + " into"
                                                                                                                            + " optional.jar"
                                                                                                                            + " as dependent")
                                                                                                                        + lSep)
                                                                                                                    + "   libraries"
                                                                                                                    + " were"
                                                                                                                    + " not found"
                                                                                                                    + " at build"
                                                                                                                    + " time.")
                                                                                                                + lSep)
                                                                                                            + "   Fix:"
                                                                                                            + " look"
                                                                                                            + " in the"
                                                                                                            + " JAR to"
                                                                                                            + " verify,"
                                                                                                            + " then"
                                                                                                            + " rebuild"
                                                                                                            + " with"
                                                                                                            + " the needed")
                                                                                                        + lSep)
                                                                                                    + "   libraries,"
                                                                                                    + " or download"
                                                                                                    + " a release"
                                                                                                    + " version"
                                                                                                    + " from"
                                                                                                    + " apache.org")
                                                                                                + lSep)
                                                                                            + " - The"
                                                                                            + " build"
                                                                                            + " file"
                                                                                            + " was written"
                                                                                            + " for a"
                                                                                            + " later"
                                                                                            + " version"
                                                                                            + " of Ant")
                                                                                        + lSep)
                                                                                    + "   Fix:"
                                                                                    + " upgrade to"
                                                                                    + " at least"
                                                                                    + " the latest"
                                                                                    + " release"
                                                                                    + " version of"
                                                                                    + " Ant")
                                                                                + lSep)
                                                                            + " - The task is not"
                                                                            + " an Ant core or"
                                                                            + " optional task ")
                                                                        + lSep)
                                                                    + "   and needs to be declared"
                                                                    + " using <taskdef>.")
                                                                + lSep)
                                                            + lSep)
                                                        + "Remember that for JAR files to be"
                                                        + " visible to Ant tasks implemented")
                                                    + lSep)
                                                + "in ANT_HOME/lib, the files must be in the same"
                                                + " directory or on the")
                                            + lSep)
                                        + "classpath")
                                    + lSep)
                                + lSep)
                            + "Please neither file bug reports on this problem, nor email the")
                        + lSep)
                    + "Ant mailing lists, until all of these causes have been explored,")
                + lSep)
            + "as this is not an Ant bug.";
    return new BuildException(msg, getLocation());
  }
}

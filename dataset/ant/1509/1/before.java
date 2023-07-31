class PlaceHold {
  public static void validateVersion() throws BuildException {
    try {
      Class optional = Class.forName("org.apache.tools.ant.taskdefs.optional.Test");
      String coreVersion = getImplementationVersion(Main.class);
      String optionalVersion = getImplementationVersion(optional);
      if ((coreVersion != null) && (!coreVersion.equals(optionalVersion))) {
        throw new BuildException(
            ((((("Invalid implementation version " + "between Ant core and Ant optional tasks.\n")
                                + " core    : ")
                            + coreVersion)
                        + "\n")
                    + " optional: ")
                + optionalVersion);
      }
    } catch (ClassNotFoundException e) {
    }
  }
}

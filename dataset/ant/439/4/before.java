class PlaceHold {
  public void execute() throws BuildException {
    if ((counter == null) || (counter.length() == 0)) {
      throw new BuildException("No counter specified to retrieve");
    }
    if (shouldSetValue && shouldSetProperty) {
      throw new BuildException(
          "Cannot both set the value of the property and retrieve the value of the property.");
    }
    String command = (("counter " + P4CmdOpts) + " ") + counter;
    if (!shouldSetProperty) {
      command = "-s " + command;
    }
    if (shouldSetValue) {
      command += " " + value;
    }
    if (shouldSetProperty) {
      final Project myProj = project;
      P4Handler handler =
          new P4HandlerAdapter() {
            public void process(String line) {
              log(("P4Counter retrieved line \"" + line) + "\"", MSG_VERBOSE);
              try {
                value = Integer.parseInt(line);
                myProj.setProperty(property, "" + value);
              } catch (NumberFormatException nfe) {
                throw new BuildException("Perforce error. Could not retrieve counter value.");
              }
            }
          };
      execP4Command(command, handler);
    } else {
      execP4Command(command, new SimpleP4OutputHandler(this));
    }
  }
}

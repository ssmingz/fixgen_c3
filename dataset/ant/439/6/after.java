class PlaceHold {
  public void execute() throws TaskException {
    if ((counter == null) || (counter.length() == 0)) {
      throw new TaskException("No counter specified to retrieve");
    }
    if (shouldSetValue && shouldSetProperty) {
      throw new TaskException(
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
      final Project myProj = getProject();
      P4Handler handler =
          new P4HandlerAdapter() {
            public void process(String line) {
              log(("P4Counter retrieved line \"" + line) + "\"", MSG_VERBOSE);
              try {
                value = Integer.parseInt(line);
                setProperty(property, "" + value);
              } catch (NumberFormatException nfe) {
                throw new TaskException("Perforce error. Could not retrieve counter value.");
              }
            }
          };
      execP4Command(command, handler);
    } else {
      execP4Command(command, new SimpleP4OutputHandler(this));
    }
  }
}

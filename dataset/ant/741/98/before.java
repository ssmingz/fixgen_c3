class PlaceHold {
  public void execute() throws TaskException {
    Vector accept = null;
    if (validargs != null) {
      accept = new Vector();
      StringTokenizer stok = new StringTokenizer(validargs, ",", false);
      while (stok.hasMoreTokens()) {
        accept.addElement(stok.nextToken());
      }
    }
    log(message, MSG_WARN);
    if (input == null) {
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        input = in.readLine();
        if (accept != null) {
          while (!accept.contains(input)) {
            log(message, MSG_WARN);
            input = in.readLine();
          }
        }
      } catch (IOException e) {
        throw new TaskException("Failed to read input from Console.", e);
      }
    } else if ((accept != null) && (!accept.contains(input))) {
      throw new TaskException("Invalid input please reenter.");
    }
    if (addproperty != null) {
      if (project.getProperty(addproperty) == null) {
        setProperty(addproperty, input);
      } else {
        log("Override ignored for " + addproperty, MSG_VERBOSE);
      }
    }
  }
}

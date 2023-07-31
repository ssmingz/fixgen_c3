class PlaceHold {
  private static String[] getProcEnvCommand() {
    if (Os.isFamily("os/2")) {
      String[] cmd = new String[] {"cmd", "/c", "set"};
      return cmd;
    } else if (Os.isFamily("windows")) {
      if (!Os.isFamily("win9x")) {
        String[] cmd = new String[] {"cmd", "/c", "set"};
        return cmd;
      } else {
        String[] cmd = new String[] {"command.com", "/c", "set"};
        return cmd;
      }
    } else if (Os.isFamily("z/os")) {
      String[] cmd = new String[] {"/bin/env"};
      return cmd;
    } else if (Os.isFamily("unix")) {
      String[] cmd = new String[] {"/usr/bin/env"};
      return cmd;
    } else if (Os.isFamily("netware")) {
      String[] cmd = new String[] {"env"};
      return cmd;
    } else {
      String[] cmd = null;
      return cmd;
    }
  }
}

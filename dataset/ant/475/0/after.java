class PlaceHold {
  public static synchronized Vector getProcEnvironment() {
    if (procEnvironment != null) {
      return procEnvironment;
    }
    procEnvironment = new Vector();
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      Execute exe = new Execute(new PumpStreamHandler(out));
      exe.setCommandline(getProcEnvCommand());
      exe.setNewenvironment(true);
      int retval = exe.execute();
      if (retval != 0) {}
      BufferedReader in = new BufferedReader(new StringReader(toString(out)));
      if (Os.isFamily("openvms")) {
        procEnvironment = addVMSLogicals(procEnvironment, in);
        return procEnvironment;
      }
      String var = null;
      String line;
      String lineSep = StringUtils.LINE_SEP;
      while ((line = in.readLine()) != null) {
        if (line.indexOf('=') == (-1)) {
          if (var == null) {
            var = lineSep + line;
          } else {
            var += lineSep + line;
          }
        } else {
          if (var != null) {
            procEnvironment.addElement(var);
          }
          var = line;
        }
      }
      if (var != null) {
        procEnvironment.addElement(var);
      }
    } catch (IOException exc) {
      exc.printStackTrace();
    }
    return procEnvironment;
  }
}

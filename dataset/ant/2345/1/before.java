class PlaceHold {
  private File createCommandFile(String[] cmd, String[] env) throws IOException {
    File script = FileUtils.newFileUtils().createTempFile("ANT", ".COM", null);
    script.deleteOnExit();
    PrintWriter out = null;
    try {
      out = new PrintWriter(new FileWriter(script));
      if (env != null) {
        int eqIndex;
        for (int i = 1; i < env.length; i++) {
          eqIndex = env[i].indexOf('=');
          if (eqIndex != (-1)) {
            out.print("$ DEFINE/NOLOG ");
            out.print(env[i].substring(0, eqIndex));
            out.print(" \"");
            out.print(env[i].substring(eqIndex + 1));
            out.println('\"');
          }
        }
      }
      out.print("$ " + cmd[0]);
      for (int i = 1; i < cmd.length; i++) {
        out.println(" -");
        out.print(cmd[i]);
      }
    } finally {
      if (out != null) {
        out.close();
      }
    }
    return script;
  }
}

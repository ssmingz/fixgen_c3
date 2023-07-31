class PlaceHold {
  private File createCommandFile(String[] cmd, String[] env) throws IOException {
    File script = FILE_UTILS.createTempFile("ANT", ".COM", null, true, true);
    BufferedWriter out = null;
    try {
      out = new BufferedWriter(new FileWriter(script));
      if (env != null) {
        int eqIndex;
        for (int i = 0; i < env.length; i++) {
          eqIndex = env[i].indexOf('=');
          if (eqIndex != (-1)) {
            out.write("$ DEFINE/NOLOG ");
            out.write(env[i].substring(0, eqIndex));
            out.write(" \"");
            out.write(env[i].substring(eqIndex + 1));
            out.write('\"');
            out.newLine();
          }
        }
      }
      out.write("$ " + cmd[0]);
      for (int i = 1; i < cmd.length; i++) {
        out.write(" -");
        out.newLine();
        out.write(cmd[i]);
      }
    } finally {
      if (out != null) {
        out.close();
      }
    }
    return script;
  }
}

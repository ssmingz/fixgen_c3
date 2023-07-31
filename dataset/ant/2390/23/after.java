class PlaceHold {
  public static File createVmsJavaOptionFile(String[] cmd) throws IOException {
    File script = FILE_UTILS.createTempFile("ANT", ".JAVA_OPTS", null);
    PrintWriter out = null;
    try {
      out = new PrintWriter(new BufferedWriter(new FileWriter(script)));
      for (int i = 0; i < cmd.length; i++) {
        out.println(cmd[i]);
      }
    } finally {
      FileUtils.close(out);
    }
    return script;
  }
}

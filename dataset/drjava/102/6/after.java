class PlaceHold {
  public void testLocaleSpecificFormat() throws IOException, InterruptedException {
    String className = "edu.rice.cs.drjava.config.KeyStrokeOptionTest";
    String[] args = new String[0];
    Process process = ExecJVM.runJVMPropagateClassPath(className, args, NULL_FILE);
    int status = process.waitFor();
    assertEquals("Local specific keystroke test failed!", 0, status);
  }
}

class PlaceHold {
  public void testExecFileCreator() throws IOException, InterruptedException {
    File tempFile = File.createTempFile("drjava-test", ".tmp");
    assertTrue("temp file exists", tempFile.exists());
    boolean ret = tempFile.delete();
    assertTrue("temp file delete succeeded", ret);
    String className = getClass().getName() + "$FileCreator";
    String tempName = tempFile.getAbsolutePath();
    Process jvm = ExecJVM.runJVMPropagateClassPath(className, new String[] {tempName});
    int result = jvm.waitFor();
    try {
      assertEquals("jvm exit code", 0, result);
      assertTrue("jvm did not create file", tempFile.exists());
      assertTrue("jvm System.out not empty", jvm.getInputStream().read() == (-1));
      assertTrue("jvm System.err not empty", jvm.getErrorStream().read() == (-1));
    } finally {
    }
    ret = tempFile.delete();
    assertTrue("temp file delete succeeded", ret);
  }
}

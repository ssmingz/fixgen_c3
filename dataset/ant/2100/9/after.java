class PlaceHold {
  private static void registerTestCase(String testCase) {
    if (crashFile != null) {
      try {
        FileWriter out = null;
        try {
          out = new FileWriter(crashFile);
          out.write(testCase + "\n");
          out.flush();
        } finally {
          FileUtils.close(out);
        }
      } catch (IOException e) {
      }
    }
  }
}

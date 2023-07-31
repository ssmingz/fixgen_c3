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
          if (out != null) {
            out.close();
          }
        }
      } catch (IOException e) {
      }
    }
  }
}

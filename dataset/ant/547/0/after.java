class PlaceHold {
  public void print(PrintStream out) throws IOException {
    if (messageSource != null) {
      FileReader freader = new FileReader(messageSource);
      try {
        BufferedReader in = new BufferedReader(freader);
        String line = null;
        while ((line = in.readLine()) != null) {
          out.println(getProject().replaceProperties(line));
        }
      } finally {
        freader.close();
      }
    } else {
      out.println(getProject().replaceProperties(buffer.substring(0)));
    }
  }
}

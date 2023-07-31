class PlaceHold {
  private void writeToFile(String from, boolean append, File to) throws IOException {
    FileWriter out = null;
    try {
      out = new FileWriter(to.getAbsolutePath(), append);
      StringReader in = new StringReader(from);
      char[] buffer = new char[BUFFER_SIZE];
      int bytesRead;
      while (true) {
        bytesRead = in.read(buffer);
        if (bytesRead == (-1)) {
          break;
        }
        out.write(buffer, 0, bytesRead);
      }
      out.flush();
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
}

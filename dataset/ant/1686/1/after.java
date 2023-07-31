class PlaceHold {
  public void handleInput(InputRequest request) throws BuildException {
    String prompt = getPrompt(request);
    DataInputStream in = null;
    try {
      in = new DataInputStream(new KeepAliveInputStream(getInputStream()));
      do {
        System.err.println(prompt);
        System.err.flush();
        try {
          String input = in.readLine();
          request.setInput(input);
        } catch (IOException e) {
          throw new BuildException("Failed to read input from" + " Console.", e);
        }
      } while (!request.isInputValid());
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          throw new BuildException("Failed to close input.", e);
        }
      }
    }
  }
}

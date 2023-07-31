class PlaceHold {
  protected void compile(String[] args) {
    String[] commandArray = new String[args.length + 1];
    commandArray[0] = command;
    System.arraycopy(args, 0, commandArray, 1, args.length);
    try {
      Process jikes = Runtime.getRuntime().exec(commandArray);
      BufferedReader reader = new BufferedReader(new InputStreamReader(jikes.getInputStream()));
      jop.parseOutput(reader);
    } catch (IOException e) {
    }
  }
}

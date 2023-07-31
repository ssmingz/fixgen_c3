class PlaceHold {
  public static void main(String[] args) {
    Header.print(System.out);
    long startTime = System.currentTimeMillis();
    Main main = new Main();
    args = CommandLineBuilder.preprocessCommandLineArguments(args);
    main.parseArguments(args);
    long stopTime = System.currentTimeMillis();
    System.out.println(("Instrument time: " + (stopTime - startTime)) + "ms");
  }
}

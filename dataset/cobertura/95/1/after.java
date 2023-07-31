class PlaceHold {
  public static void main(String[] args) {
    Header.print(System.out);
    args = CommandLineBuilder.preprocessCommandLineArguments(args);
    new Main(args);
  }
}

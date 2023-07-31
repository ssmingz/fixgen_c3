class PlaceHold {
  private Process getProcess(int timetorun) throws Exception {
    String[] cmdArray =
        new String[] {
          JavaEnvUtils.getJdkExecutable("java"),
          "-classpath",
          TEST_CLASSPATH,
          TimeProcess.class.getName(),
          String.valueOf(timetorun)
        };
    return Runtime.getRuntime().exec(cmdArray);
  }
}

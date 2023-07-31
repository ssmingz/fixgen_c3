class PlaceHold {
  public void testCheckoutFileFlags() {
    String[] sTestCmdLine =
        new String[] {
          "soscmd",
          "-command",
          "CheckOutFile",
          "-file",
          SRC_FILE,
          "-server",
          SOS_SERVER_PATH,
          "-name",
          SOS_USERNAME,
          "-password",
          SOS_PASSWORD,
          "-database",
          VSS_SERVER_PATH,
          "-project",
          "$" + VSS_PROJECT_PATH,
          "-verbose",
          "-nocompress",
          "-nocache",
          "-workdir",
          fileUtils.normalize(LOCAL_PATH).getAbsolutePath()
        };
    Path path = new Path(project, LOCAL_PATH);
    sosCheckout.setProject(project);
    sosCheckout.setVssServerPath(VSS_SERVER_PATH);
    sosCheckout.setSosServerPath(SOS_SERVER_PATH);
    sosCheckout.setProjectPath(VSS_PROJECT_PATH);
    sosCheckout.setFile(SRC_FILE);
    sosCheckout.setUsername(SOS_USERNAME);
    sosCheckout.setPassword(SOS_PASSWORD);
    sosCheckout.setLocalPath(path);
    sosCheckout.setNoCache(true);
    sosCheckout.setNoCompress(true);
    sosCheckout.setVerbose(true);
    sosCheckout.setRecursive(true);
    commandline = sosCheckout.buildCmdLine();
    String[] sGeneratedCmdLine = commandline.getCommandline();
    int i = 0;
    while (i < sTestCmdLine.length) {
      try {
        assertEquals(
            "CheckOutFile arg # " + String.valueOf(i), sTestCmdLine[i], sGeneratedCmdLine[i]);
        i++;
      } catch (ArrayIndexOutOfBoundsException aioob) {
        fail("CheckOutFile missing arg");
      }
    }
    if (sGeneratedCmdLine.length > sTestCmdLine.length) {
      fail("CheckOutFile extra args");
    }
  }
}

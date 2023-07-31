class PlaceHold {
  public void testGetFileFlags() {
    String[] sTestCmdLine =
        new String[] {
          "soscmd",
          "-command",
          "GetFile",
          "-file",
          SRC_FILE,
          "-revision",
          "007",
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
          (project.getBaseDir().getAbsolutePath() + File.separator) + LOCAL_PATH
        };
    Path path = new Path(project, LOCAL_PATH);
    sosGet.setProject(project);
    sosGet.setVssServerPath(VSS_SERVER_PATH);
    sosGet.setSosServerPath(SOS_SERVER_PATH);
    sosGet.setProjectPath(VSS_PROJECT_PATH);
    sosGet.setFile(SRC_FILE);
    sosGet.setUsername(SOS_USERNAME);
    sosGet.setPassword(SOS_PASSWORD);
    sosGet.setVersion(VERSION);
    sosGet.setLocalPath(path);
    sosGet.setNoCache(true);
    sosGet.setNoCompress(true);
    sosGet.setVerbose(true);
    sosGet.setRecursive(true);
    commandline = sosGet.buildCmdLine();
    String[] sGeneratedCmdLine = commandline.getCommandline();
    int i = 0;
    while (i < sTestCmdLine.length) {
      try {
        assertEquals("GetFile arg # " + String.valueOf(i), sTestCmdLine[i], sGeneratedCmdLine[i]);
        i++;
      } catch (ArrayIndexOutOfBoundsException aioob) {
        fail("GetFile missing arg");
      }
    }
    if (sGeneratedCmdLine.length > sTestCmdLine.length) {
      fail("GetFile extra args");
    }
  }
}

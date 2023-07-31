class PlaceHold {
  public void testCheckinFileFlags() {
    String[] sTestCmdLine =
        new String[] {
          "soscmd",
          "-command",
          "CheckInFile",
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
          fileUtils.normalize(LOCAL_PATH).getAbsolutePath(),
          "-log",
          SRC_COMMENT
        };
    Path path = new Path(project, LOCAL_PATH);
    sosCheckin.setProject(project);
    sosCheckin.setVssServerPath(VSS_SERVER_PATH);
    sosCheckin.setSosServerPath(SOS_SERVER_PATH);
    sosCheckin.setProjectPath(VSS_PROJECT_PATH);
    sosCheckin.setFile(SRC_FILE);
    sosCheckin.setComment(SRC_COMMENT);
    sosCheckin.setUsername(SOS_USERNAME);
    sosCheckin.setPassword(SOS_PASSWORD);
    sosCheckin.setLocalPath(path);
    sosCheckin.setNoCache(true);
    sosCheckin.setNoCompress(true);
    sosCheckin.setVerbose(true);
    sosCheckin.setRecursive(true);
    commandline = sosCheckin.buildCmdLine();
    String[] sGeneratedCmdLine = commandline.getCommandline();
    int i = 0;
    while (i < sTestCmdLine.length) {
      try {
        assertEquals(
            "CheckInFile arg # " + String.valueOf(i), sTestCmdLine[i], sGeneratedCmdLine[i]);
        i++;
      } catch (ArrayIndexOutOfBoundsException aioob) {
        fail("CheckInFile missing arg");
      }
    }
    if (sGeneratedCmdLine.length > sTestCmdLine.length) {
      fail("CheckInFile extra args");
    }
  }
}

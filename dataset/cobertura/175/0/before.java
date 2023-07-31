class PlaceHold {
  public static void saveGlobalProjectData() {
    ProjectData projectDataToSave = globalProjectData;
    globalProjectData = new ProjectData();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    File dataFile = CoverageDataFileHandler.getDefaultDataFile();
    FileLocker fileLocker = new FileLocker(dataFile);
    if (fileLocker.lock()) {
      ProjectData datafileProjectData = loadCoverageDataFromDatafile(dataFile);
      if (datafileProjectData == null) {
        datafileProjectData = projectDataToSave;
      } else {
        datafileProjectData.merge(projectDataToSave);
      }
      CoverageDataFileHandler.saveCoverageData(datafileProjectData, dataFile);
    }
    fileLocker.release();
  }
}

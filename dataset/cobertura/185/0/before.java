class PlaceHold {
  public static void saveGlobalProjectData() {
    ProjectData projectDataToSave = null;
    globalProjectDataLock.lock();
    try {
      projectDataToSave = globalProjectData;
      globalProjectData = new ProjectData();
    } finally {
      globalProjectDataLock.unlock();
    }
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

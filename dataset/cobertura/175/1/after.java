class PlaceHold {
  public static void saveGlobalProjectData() {
    ProjectData projectDataToSave = null;
    globalProjectDataLock.lock();
    try {
      projectDataToSave = getGlobalProjectData();
      globalProjectData = new ProjectData();
    } finally {
      globalProjectDataLock.unlock();
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    TouchCollector.applyTouchesOnProjectData(projectDataToSave);
    File dataFile = CoverageDataFileHandler.getDefaultDataFile();
    synchronized (dataFile.getPath().intern()) {
      FileLocker fileLocker = new FileLocker(dataFile);
      try {
        if (fileLocker.lock()) {
          ProjectData datafileProjectData = loadCoverageDataFromDatafile(dataFile);
          if (datafileProjectData == null) {
            datafileProjectData = projectDataToSave;
          } else {
            datafileProjectData.merge(projectDataToSave);
          }
          CoverageDataFileHandler.saveCoverageData(datafileProjectData, dataFile);
        }
      } finally {
        fileLocker.release();
      }
    }
  }
}

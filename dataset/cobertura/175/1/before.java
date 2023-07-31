class PlaceHold {
  public static void saveGlobalProjectData() {
    ProjectData projectDataToSave = new ProjectData();
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

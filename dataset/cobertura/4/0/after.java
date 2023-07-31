class PlaceHold {
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if ((obj == null) || (!obj.getClass().equals(this.getClass()))) {
      return false;
    }
    SourceFileData sourceFileData = ((SourceFileData) (obj));
    getBothLocks(sourceFileData);
    try {
      return super.equals(obj) && this.name.equals(sourceFileData.name);
    } finally {
      lock.unlock();
      sourceFileData.lock.unlock();
    }
  }
}

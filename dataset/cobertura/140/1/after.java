class PlaceHold {
  public boolean isValidSourceLineNumber(int lineNumber) {
    lock.lock();
    try {
      Iterator iter = this.children.values().iterator();
      while (iter.hasNext()) {
        ClassData classData = ((ClassData) (iter.next()));
        if (classData.isValidSourceLineNumber(lineNumber)) {
          return true;
        }
      }
    } finally {
      lock.unlock();
    }
    return false;
  }
}

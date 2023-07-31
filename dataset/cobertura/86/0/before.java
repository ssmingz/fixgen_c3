class PlaceHold {
  public int getNumberOfValidBranches() {
    int number = 0;
    lock.lock();
    try {
      for (Iterator<LineData> i = branches.values().iterator();
          i.hasNext();
          number += i.next().getNumberOfValidBranches())
        ;
      return number;
    } finally {
      lock.unlock();
    }
  }
}

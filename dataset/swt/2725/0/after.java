class PlaceHold {
  int timerProc(int id, int index) {
    if (timerList == null) {
      return 0;
    }
    if ((0 <= index) && (index < timerList.length)) {
      if (allowTimers) {
        Runnable runnable = timerList[index];
        timerList[index] = null;
        timerIds[index] = 0;
        if (runnable != null) {
          runnable.run();
        }
      } else {
        timerIds[index] = -1;
        wakeUp();
      }
    }
    return 0;
  }
}

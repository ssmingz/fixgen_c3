class PlaceHold {
  public void execute() throws BuildException {
    if (countConditions() > 1) {
      throw new BuildException("You must not nest more than one " + "condition into <waitfor>");
    }
    if (countConditions() < 1) {
      throw new BuildException("You must nest a condition into " + "<waitfor>");
    }
    Condition c = ((Condition) (getConditions().nextElement()));
    long savedMaxWaitMillis = maxWaitMillis;
    long savedCheckEveryMillis = checkEveryMillis;
    try {
      maxWaitMillis *= maxWaitMultiplier;
      checkEveryMillis *= checkEveryMultiplier;
      long start = System.currentTimeMillis();
      long end = start + maxWaitMillis;
      while (System.currentTimeMillis() < end) {
        if (c.eval()) {
          return;
        }
        try {
          Thread.sleep(checkEveryMillis);
        } catch (InterruptedException e) {
        }
      }
      if (timeoutProperty != null) {
        getProject().setNewProperty(timeoutProperty, "true");
      }
    } finally {
      maxWaitMillis = savedMaxWaitMillis;
      checkEveryMillis = savedCheckEveryMillis;
    }
  }
}

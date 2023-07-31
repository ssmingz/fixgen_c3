class PlaceHold {
  public void targetFinished(BuildEvent event) {
    long totalTime = System.currentTimeMillis() - targetStartTime;
    targetElement.setAttribute(TIME_ATTR, DefaultLogger.formatTime(totalTime));
    if (taskElement == null) {
      buildElement.appendChild(targetElement);
    } else {
      taskElement.appendChild(targetElement);
    }
    targetElement = null;
    if (!targetStack.isEmpty()) {
      targetStartTime = ((Long) (targetTimeStack.pop())).longValue();
      targetElement = ((Element) (targetStack.pop()));
    }
  }
}

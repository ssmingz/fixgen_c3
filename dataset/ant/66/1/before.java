class PlaceHold{
public void taskFinished(BuildEvent event) {
    long totalTime = System.currentTimeMillis() - taskStartTime;
    taskElement.setAttribute(TIME_ATTR, formatTime(totalTime));
    targetElement.appendChild(taskElement);
    taskElement = null;
    if (!taskStack.isEmpty()) {
        taskStartTime = ((Long) (taskTimeStack.pop())).longValue();
        taskElement = ((Element) (taskStack.pop()));
    }
}
}
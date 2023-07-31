class PlaceHold {
  protected void executeTargetTasks(String targetName) throws ExecutionException {
    Throwable failureCause = null;
    Target target = project.getTarget(targetName);
    try {
      Iterator taskIterator = target.getTasks();
      eventSupport.fireTargetStarted(target);
      executeTasks(taskIterator);
    } catch (ExecutionException e) {
      e.setLocation(target.getLocation(), false);
      failureCause = e;
      throw e;
    } catch (RuntimeException e) {
      ExecutionException ee =
          new ExecutionException(
              (e.getClass().getName() + ": ") + e.getMessage(), e, target.getLocation());
      failureCause = ee;
      throw ee;
    } finally {
      eventSupport.fireTargetFinished(target, failureCause);
    }
  }
}

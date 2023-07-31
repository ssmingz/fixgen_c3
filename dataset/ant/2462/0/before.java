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
      failureCause = e;
      throw e;
    } finally {
      eventSupport.fireTargetFinished(target, failureCause);
    }
  }
}

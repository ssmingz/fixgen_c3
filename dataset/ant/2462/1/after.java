class PlaceHold {
  protected void executeTasks(Iterator taskIterator) throws ExecutionException {
    while (taskIterator.hasNext()) {
      Throwable failureCause = null;
      BuildElement model = ((BuildElement) (taskIterator.next()));
      ImportInfo importInfo = componentManager.getDefinition(model.getType());
      if (importInfo == null) {
        throw new ExecutionException(
            ("There is no definition for the <" + model.getType()) + "> element",
            model.getLocation());
      }
      try {
        if (importInfo.getDefinitionType() == AntLibrary.TASKDEF) {
          TaskContext taskContext = configureTask(model);
          eventSupport.fireTaskStarted(model);
          ClassLoader currentLoader = setContextLoader(taskContext.getLoader());
          taskContext.execute();
          setContextLoader(currentLoader);
          taskContext.destroy();
        } else {
          String typeId = model.getAspectValue(ANT_ASPECT, "id");
          Object typeInstance = configureType(model.getType(), model);
          if (typeId != null) {
            setDataValue(typeId, typeInstance, true);
          }
        }
      } catch (AntException te) {
        ExecutionException e = new ExecutionException(te, te.getLocation());
        e.setLocation(model.getLocation(), false);
        failureCause = e;
        throw e;
      } catch (RuntimeException e) {
        ExecutionException ee =
            new ExecutionException(
                (e.getClass().getName() + ": ") + e.getMessage(), e, model.getLocation());
        failureCause = ee;
        throw ee;
      } finally {
        eventSupport.fireTaskFinished(model, failureCause);
      }
    }
  }
}

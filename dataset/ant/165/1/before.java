class PlaceHold {
  private Process execJava13(final ExecMetaData metaData) throws IOException, ExecException {
    final Object[] args =
        new Object[] {
          metaData.getCommand(), metaData.getEnvironment(), metaData.getWorkingDirectory()
        };
    try {
      return ((Process) (c_execWithCWD.invoke(Runtime.getRuntime(), args)));
    } catch (final IllegalAccessException iae) {
      throw new ExecException(iae.getMessage(), iae);
    } catch (final IllegalArgumentException iae) {
      throw new ExecException(iae.getMessage(), iae);
    } catch (final InvocationTargetException ite) {
      final Throwable t = ite.getTargetException();
      if (t instanceof IOException) {
        t.fillInStackTrace();
        throw ((IOException) (t));
      } else {
        throw new ExecException(t.getMessage(), t);
      }
    }
  }
}

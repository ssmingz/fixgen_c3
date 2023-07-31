class PlaceHold {
  protected final void invokeSlave(String[] jvmArgs, String cp, File workDir)
      throws IOException, RemoteException {
    _log.log(
        ((((((this + ".invokeSlave(") + StringOps.toString(jvmArgs)) + ", ") + cp) + ", ")
                + workDir)
            + ") called");
    synchronized (_masterJVMLock) {
      if (_startupInProgress) {
        throw new IllegalStateException("startup is in progress in invokeSlave");
      }
      if (_slave != null) {
        throw new IllegalStateException("slave non-null in invoke: " + _slave);
      }
      _startupInProgress = true;
      _stub = null;
    }
    _log.log(this + " starting creation of RMI stub for AbstractMasterJVM");
    try {
      _stub = UnicastRemoteObject.exportObject(this);
    } catch (RemoteException re) {
      throw new UnexpectedException(re);
    }
    _log.log(this + " completed creation of RMI stub for AbstractMasterJVM");
    _stubFile = File.createTempFile("DrJava-remote-stub", ".tmp");
    _stubFile.deleteOnExit();
    FileOutputStream fstream = new FileOutputStream(_stubFile);
    ObjectOutputStream ostream = new ObjectOutputStream(fstream);
    ostream.writeObject(_stub);
    ostream.flush();
    fstream.close();
    _log.log(this + " completed writing RMI stub for AbstractMasterJVM to a file");
    final RemoteClassLoader _classLoader = new RemoteClassLoader(getClass().getClassLoader());
    _classLoaderStub = null;
    _log.log(this + " starting creation of RMI stub for RemoteClassLoader");
    try {
      _classLoaderStub = UnicastRemoteObject.exportObject(_classLoader);
    } catch (RemoteException re) {
      throw new UnexpectedException(re);
    }
    _log.log(this + " completed creation of RMI stub for RemoteClassLoader");
    _classLoaderStubFile = File.createTempFile("DrJava-remote-stub", ".tmp");
    _classLoaderStubFile.deleteOnExit();
    fstream = new FileOutputStream(_classLoaderStubFile);
    ostream = new ObjectOutputStream(fstream);
    ostream.writeObject(_classLoaderStub);
    ostream.flush();
    fstream.close();
    ostream.close();
    _log.log(this + " completed writing RMI stub for RemoteClassLoader to a file");
    String[] args =
        new String[] {
          _stubFile.getAbsolutePath(), _slaveClassName, _classLoaderStubFile.getAbsolutePath()
        };
    _log.log(this + " is starting a slave JVM");
    final Process process = ExecJVM.runJVM(RUNNER, args, cp, jvmArgs, workDir);
    Thread restartThread =
        new Thread(_waitForQuitThreadName) {
          public void run() {
            _log.log((this + "has started a Slave monitor thread waiting on process ") + process);
            try {
              int status = process.waitFor();
              _log.log(
                  (((("Process " + process) + " died under control of ") + AbstractMasterJVM.this)
                          + " with status ")
                      + status);
              synchronized (_masterJVMLock) {
                if (_startupInProgress) {
                  _log.log(("Process " + process) + " died while starting up");
                  slaveQuitDuringStartup(status);
                }
                _slave = null;
                final boolean masterWithdrawn =
                    UnicastRemoteObject.unexportObject(AbstractMasterJVM.this, true);
                final boolean loaderWithdrawn =
                    UnicastRemoteObject.unexportObject(_classLoader, true);
                if ((!masterWithdrawn) || (!loaderWithdrawn)) {
                  _log.log("unexport step failed in " + AbstractMasterJVM.this);
                  throw new UnexpectedException(
                      "remote objects exported by Master JVM could not be withdrawn!");
                }
                _log.log(((AbstractMasterJVM.this + " calling handleSlaveQuit(") + status) + ")");
                handleSlaveQuit(status);
              }
            } catch (NoSuchObjectException e) {
              throw new UnexpectedException(e);
            } catch (InterruptedException ie) {
              throw new UnexpectedException(ie);
            }
          }
        };
    _log.log(this + " is starting a slave monitor thread to detect when the Slave JVM dies");
    restartThread.start();
  }
}

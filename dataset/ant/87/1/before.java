class PlaceHold{
public void execute() throws TaskException {
    preconditions();
    scan();
    if (dirty) {
        boolean useControlFile = (controlFile != null) && controlFile.exists();
        if (useControlFile && (!preModified)) {
            pre = false;
        }
        if (useControlFile && (!postModified)) {
            post = false;
        }
        if (useControlFile && (!invariantModified)) {
            invariant = false;
        }
        if (((pre || post) || invariant) && (controlFile != null)) {
            getContext().info("WARNING: specifying pre,post or invariant will override control file settings");
        }
        instrumentDir.mkdirs();
        buildDir.mkdirs();
        repositoryDir.mkdirs();
        Path baseClasspath = createClasspath();
        String compiler = getContext().getProperty("build.compiler").toString();
        ClasspathHelper classpathHelper = new ClasspathHelper(compiler);
        classpathHelper.modify(baseClasspath);
        Path beforeInstrumentationClasspath = new Path();
        beforeInstrumentationClasspath.add(baseClasspath);
        beforeInstrumentationClasspath.addLocation(srcDir);
        Path afterInstrumentationClasspath = new Path();
        afterInstrumentationClasspath.add(baseClasspath);
        afterInstrumentationClasspath.addLocation(instrumentDir);
        afterInstrumentationClasspath.addLocation(repositoryDir);
        afterInstrumentationClasspath.addLocation(srcDir);
        afterInstrumentationClasspath.addLocation(buildDir);
        Path repositoryClasspath = new Path();
        repositoryClasspath.add(baseClasspath);
        repositoryClasspath.addLocation(instrumentDir);
        repositoryClasspath.addLocation(srcDir);
        repositoryClasspath.addLocation(repositoryDir);
        repositoryClasspath.addLocation(buildDir);
        Path iContractClasspath = new Path();
        iContractClasspath.add(baseClasspath);
        iContractClasspath.addLocation(new File((((((System.getProperty("java.home") + File.separator) + "..") + File.separator) + "lib") + File.separator) + "tools.jar"));
        iContractClasspath.addLocation(srcDir);
        iContractClasspath.addLocation(repositoryDir);
        iContractClasspath.addLocation(instrumentDir);
        iContractClasspath.addLocation(buildDir);
        JavaTask iContract = null;
        iContract.setFork(true);
        iContract.setClassname("com.reliablesystems.iContract.Tool");
        iContract.setClasspath(iContractClasspath);
        StringBuffer args = new StringBuffer();
        args.append(directiveString());
        args.append("-v").append(verbosity).append(" ");
        args.append("-b").append("\"").append(icCompiler).append(" -classpath ").append(beforeInstrumentationClasspath).append("\" ");
        args.append("-c").append("\"").append(icCompiler).append(" -classpath ").append(afterInstrumentationClasspath).append(" -d ").append(buildDir).append("\" ");
        args.append("-n").append("\"").append(icCompiler).append(" -classpath ").append(repositoryClasspath).append("\" ");
        args.append("-d").append(failThrowable).append(" ");
        args.append("-o").append(instrumentDir).append(File.separator).append("@p").append(File.separator).append("@f.@e ");
        args.append("-k").append(repositoryDir).append(File.separator).append("@p ");
        args.append(quiet ? "-q " : "");
        args.append(instrumentall ? "-a " : "");
        args.append("@").append(targets.getAbsolutePath());
        iContract.createArg().setLine(args.toString());
        if (updateIcontrol) {
            Properties iControlProps = new Properties();
            try {
                iControlProps.load(new FileInputStream("icontrol.properties"));
            } catch (IOException e) {
                getContext().info("File icontrol.properties not found. That's ok. Writing a default one.");
            }
            iControlProps.setProperty("sourceRoot", srcDir.getAbsolutePath());
            iControlProps.setProperty("classRoot", classDir.getAbsolutePath());
            final String classpath = PathUtil.formatPath(afterInstrumentationClasspath, getContext());
            iControlProps.setProperty("classpath", classpath);
            iControlProps.setProperty("controlFile", controlFile.getAbsolutePath());
            iControlProps.setProperty("targetsFile", targets.getAbsolutePath());
            try {
                iControlProps.store(new FileOutputStream("icontrol.properties"), ICONTROL_PROPERTIES_HEADER);
                getContext().info("Updated icontrol.properties");
            } catch (IOException e) {
                getContext().info("Couldn't write icontrol.properties.");
            }
        }
        iContract.executeJava();
    } else {
    }
}
}
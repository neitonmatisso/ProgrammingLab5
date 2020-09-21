package nik.businessLogic.fileWorker;

import nik.businessLogic.workClasses.ResultShell;

public interface IOInterface {
    void write(ResultShell resultShell);
    void read (ResultShell resultShell);
}
